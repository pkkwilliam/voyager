package mo.bitcode.kyrie.service.pool.confirming_game;

import mo.bitcode.kyrie.service.notify.NotifyService;
import mo.bitcode.kyrie.service.pool.confirming_game.model.ConfirmingGame;
import mo.bitcode.kyrie.service.pool.on_going_game.OnGoingGamePoolService;
import mo.bitcode.kyrie.service.pool.on_going_game.model.OnGoingGame;
import mo.bitcode.kyrie.service.team.model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractConfirmingGamePoolService implements ConfirmingGamePoolService {

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractConfirmingGamePoolService.class);

  private NotifyService notifyService;
  private OnGoingGamePoolService onGoingGamePoolService;

  public AbstractConfirmingGamePoolService(NotifyService notifyService, OnGoingGamePoolService onGoingGamePoolService) {
    this.notifyService = notifyService;
    this.onGoingGamePoolService = onGoingGamePoolService;
  }

  protected abstract ConfirmingGame findByTeam(Team team);

  @Override
  public void teamConfirm(Team team) {
    final ConfirmingGame confirmingGame = this.findByTeam(team);
    final int index = confirmingGame.getTeamIndex(team);
    final Team actualTeam = confirmingGame.getTeam(index);

    // put team into the confirmed
    confirmingGame.getConfirmed()[index] = actualTeam;

    // Proceed only if all teams are confirmed
    final boolean teamsAllConfirmed = this.isTeamsAllConfirmed(confirmingGame);
    if (!teamsAllConfirmed) {
      return;
    }
    this.onAllTeamConfirmed(confirmingGame);
  }

  protected void onAllTeamConfirmed(ConfirmingGame confirmingGame) {
    final OnGoingGame onGoingGame = new OnGoingGame(confirmingGame.getTeams());
    this.onGoingGamePoolService.enter(onGoingGame);
    for (Team team: onGoingGame.getTeams()) {
      this.notifyService.notifyGameConfirmed(team);
    }
    this.quit(confirmingGame.getId());
  }

  protected boolean isTeamsAllConfirmed(ConfirmingGame confirmingGame) {
    final Team[] confirmed = confirmingGame.getConfirmed();
    return confirmed[0] != null && confirmed[1] != null;
  }

}
