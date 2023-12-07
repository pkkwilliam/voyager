package mo.bitcode.kyrie.service.game;

import mo.bitcode.core.service.rest_template.query.AbstractQueryServiceRepository;
import mo.bitcode.core.service.rest_template.query.AbstractQueryServiceTemplate;
import mo.bitcode.kyrie.common.exception.KyrieException;
import mo.bitcode.kyrie.common.exception.KyrieExceptionCode;
import mo.bitcode.kyrie.service.GamePoolService;
import mo.bitcode.kyrie.service.game.model.Game;
import mo.bitcode.kyrie.service.game.model.GameQueryRequest;
import mo.bitcode.kyrie.service.notify.NotifyService;
import mo.bitcode.kyrie.service.rating.RatingService;
import mo.bitcode.kyrie.service.team.model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public abstract class GameTemplate extends AbstractQueryServiceTemplate<Game, GameQueryRequest> implements GameService {

  private static final Logger LOGGER = LoggerFactory.getLogger(GameTemplate.class);

  private GamePoolService gamePoolService;
  private NotifyService notifyService;
  private RatingService ratingService;

  public GameTemplate(AbstractQueryServiceRepository<Game> abstractQueryServiceRepository,
                      GamePoolService gamePoolService,
                      NotifyService notifyService,
                      RatingService ratingService) {
    super(abstractQueryServiceRepository);
    this.gamePoolService = gamePoolService;
    this.notifyService = notifyService;
    this.ratingService = ratingService;
  }

  protected abstract boolean isTeamInAnyPool(Team team);
  protected abstract Game match(Team team);

  @Override
  public void findMatch(Team team) {
    if (isTeamInAnyPool(team)) {
      throw new KyrieException(KyrieExceptionCode.TEAM_NOT_ALLOW_TO_ENTER_POOL);
    }
    final Game game = this.match(team);
    if (game == null) {
      this.gamePoolService.addTeamToFindMatchPool(team);
      return;
    }
    final Team priorEnterTeam = game.getTeam1();
    this.gamePoolService.removeTeamFromFindMatchPool(priorEnterTeam);
    this.gamePoolService.addGameToReadyPool(game);
    this.notifyService.notifyGameReady(game);
  }

  @Override
  public void startMatch(Game game) {
    this.gamePoolService.removeGameFromReadyPool(game);
    this.gamePoolService.addGameToProcessingPool(game);
    this.notifyService.notifyStartGame(game);
  }

  @Override
  public void endMatch(Game game) {
    this.gamePoolService.removeGameFromProcessingPool(game);
    this.ratingService.updateTeamRating(game);
    this.create(game);
  }

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }
}
