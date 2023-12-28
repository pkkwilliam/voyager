package mo.bitcode.kyrie.service.pool.on_going_game;

import mo.bitcode.kyrie.common.model.TeamSideWonResult;
import mo.bitcode.kyrie.service.game.GameService;
import mo.bitcode.kyrie.service.game.model.Game;
import mo.bitcode.kyrie.service.game.model.GameStatus;
import mo.bitcode.kyrie.service.notify.NotifyService;
import mo.bitcode.kyrie.service.pool.on_going_game.model.OnGoingGame;
import mo.bitcode.kyrie.service.pool.on_going_game.model.TeamAssertResult;
import mo.bitcode.kyrie.service.rating.RatingService;
import mo.bitcode.kyrie.service.team.model.Team;
import mo.bitcode.kyrie.util.GameUtil;

public abstract class AbstractOnGoingGamePoolService implements OnGoingGamePoolService {

  private GameService gameService;
  private NotifyService notifyService;
  private RatingService ratingService;

  public AbstractOnGoingGamePoolService(GameService gameService,
                                        NotifyService notifyService,
                                        RatingService ratingService) {
    this.gameService = gameService;
    this.notifyService = notifyService;
    this.ratingService = ratingService;
  }

  protected abstract OnGoingGame getOnGoingGame(Team team);

  @Override
  public void teamAssertGameEnd(TeamAssertResult teamAssertResult) {
    final Team team = teamAssertResult.getTeam();
    final OnGoingGame onGoingGame = this.getOnGoingGame(team);
    final int index = onGoingGame.getTeamIndex(team);
    onGoingGame.getTeamAssertResults()[index] = teamAssertResult;

    // check is teams confirm ended
    final boolean isOnGoingGameEnd = this.isOnGoingGameEnd(onGoingGame);
    if (!isOnGoingGameEnd) {
      return;
    }
    this.onGoingGameFinished(onGoingGame);
  }

  protected boolean isOnGoingGameEnd(OnGoingGame onGoingGame) {
    // TODO check if minimum time length reached (a running game should take no less than 10 minutes)
    final TeamAssertResult[] teamAssertResults = onGoingGame.getTeamAssertResults();
    return teamAssertResults[0] != null && teamAssertResults[1] != null;
  }

  protected void onGoingGameFinished(OnGoingGame onGoingGame) {
    final boolean hasDispute = this.hasDispute(onGoingGame);
    final GameStatus gameStatus = !hasDispute ? GameStatus.END : GameStatus.DISPUTE;
    final Game game = this.transformGame(onGoingGame, gameStatus);
    this.gameService.create(game);

    if (gameStatus == GameStatus.DISPUTE) {
      this.onGameEndedWithDispute(game);
    } else if (gameStatus == GameStatus.END) {
      this.onGameEndedPeacefully(game);
    }

  }

  /**
   * once get to this method, there should be already clear which team win/lose
   */
  protected void onGameEndedPeacefully(Game game) {
    this.ratingService.updateTeamRating(game);

  }

  protected void onGameEndedWithDispute(Game game) {
    this.notifyService.notifyGameDisputed(game);
  }

  protected boolean hasDispute(OnGoingGame onGoingGame) {
    final TeamAssertResult team1Result = onGoingGame.getTeamAssertResults()[0];
    final TeamAssertResult team2Result = onGoingGame.getTeamAssertResults()[1];
    final TeamSideWonResult teamSideWonResult =
            GameUtil.calculateTeamSideWonResult(team1Result.getAssertScore(), team2Result.getAssertScore());
    return teamSideWonResult == TeamSideWonResult.TEAM_1_WON || teamSideWonResult == TeamSideWonResult.TEAM_2_WON;
  }

  protected Game transformGame(OnGoingGame onGoingGame, GameStatus gameStatus) {
    final TeamAssertResult[] teamAssertResults = onGoingGame.getTeamAssertResults();
    final Game game = new Game();
    game.setTeam1(onGoingGame.getTeam1());
    game.setTeam2(onGoingGame.getTeam2());
    game.setTeam1Score(teamAssertResults[0].getAssertScore());
    game.setTeam2Score(teamAssertResults[1].getAssertScore());
    game.setGameStatus(gameStatus);
    return game;
  }

}
