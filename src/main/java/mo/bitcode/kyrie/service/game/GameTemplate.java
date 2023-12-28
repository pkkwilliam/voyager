package mo.bitcode.kyrie.service.game;

import mo.bitcode.core.service.rest_template.query.AbstractQueryServiceTemplate;
import mo.bitcode.kyrie.common.exception.KyrieException;
import mo.bitcode.kyrie.common.exception.KyrieExceptionCode;
import mo.bitcode.kyrie.service.game.model.Game;
import mo.bitcode.kyrie.service.game.model.GameQueryRequest;
import mo.bitcode.kyrie.service.pool.PoolService;
import mo.bitcode.kyrie.service.pool.confirming_game.ConfirmingGamePoolService;
import mo.bitcode.kyrie.service.pool.find_match.FindMatchPoolService;
import mo.bitcode.kyrie.service.pool.find_match.model.FindMatch;
import mo.bitcode.kyrie.service.pool.on_going_game.OnGoingGamePoolService;
import mo.bitcode.kyrie.service.pool.on_going_game.model.TeamAssertResult;
import mo.bitcode.kyrie.service.team.model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

public class GameTemplate extends AbstractQueryServiceTemplate<Game, GameQueryRequest> implements GameService {

  private static final Logger LOGGER = LoggerFactory.getLogger(GameTemplate.class);

  private FindMatchPoolService findMatchPoolService;
  private ConfirmingGamePoolService confirmingGamePoolService;
  private OnGoingGamePoolService onGoingGamePoolService;

  public GameTemplate(GameRepository gameRepository,
                      int queryRequestSize,
                      FindMatchPoolService findMatchPoolService,
                      ConfirmingGamePoolService confirmingGamePoolService,
                      OnGoingGamePoolService onGoingGamePoolService) {
    super(gameRepository, queryRequestSize);
    this.findMatchPoolService = findMatchPoolService;
    this.confirmingGamePoolService = confirmingGamePoolService;
    this.onGoingGamePoolService = onGoingGamePoolService;
  }

  @Override
  public void findMatch(Team team, long latitude, long longitude) {
    final FindMatch findMatch = new FindMatch(team);
    findMatch.setLatitude(latitude);
    findMatch.setLongitude(longitude);
    this.findMatchPoolService.enter(findMatch);
  }

  @Override
  public void startMatch(Team team) {
    this.validateTeamInPool(confirmingGamePoolService, team);
    this.confirmingGamePoolService.teamConfirm(team);
  }

  @Override
  public void endMatch(TeamAssertResult teamAssertResult) {
    this.validateTeamInPool(onGoingGamePoolService, teamAssertResult.getTeam());
    this.onGoingGamePoolService.teamAssertGameEnd(teamAssertResult);
  }

  private void validateTeamInPool(PoolService poolService, Team team) {
    final boolean teamInPool = poolService.teamInPool(team);
    if (!teamInPool) {
      throw new KyrieException(KyrieExceptionCode.TEAM_NOT_IN_POOL);
    }
  }

  @Override
  protected Specification<Game> generateBasicSpecification(GameQueryRequest gameQueryRequest) {
    return Specification.where(this.isActive(true));
  }

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }

}
