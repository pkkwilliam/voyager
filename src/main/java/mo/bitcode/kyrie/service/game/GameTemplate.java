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
import mo.bitcode.kyrie.service.team.TeamService;
import mo.bitcode.kyrie.service.team.model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public class GameTemplate extends AbstractQueryServiceTemplate<Game, GameQueryRequest> implements GameService {

  private static final Logger LOGGER = LoggerFactory.getLogger(GameTemplate.class);
  private static final int PAGINATION_REQUEST_SIZE = 20;

  private FindMatchPoolService findMatchPoolService;
  private ConfirmingGamePoolService confirmingGamePoolService;
  private OnGoingGamePoolService onGoingGamePoolService;
  private TeamService teamService;

  public GameTemplate(GameRepository gameRepository,
                      int queryRequestSize,
                      FindMatchPoolService findMatchPoolService,
                      ConfirmingGamePoolService confirmingGamePoolService,
                      OnGoingGamePoolService onGoingGamePoolService,
                      TeamService teamService) {
    super(gameRepository, queryRequestSize);
    this.findMatchPoolService = findMatchPoolService;
    this.confirmingGamePoolService = confirmingGamePoolService;
    this.onGoingGamePoolService = onGoingGamePoolService;
    this.teamService = teamService;
  }

  @Override
  public Page<Game> getByTeamId(long teamId, int pageRequest) {
    final Specification<Game> specification = Specification.where(isTeam1IdEqual(teamId)).or(isTeam2IdEqual(teamId));
    return this.queryPagination(specification, pageRequest, PAGINATION_REQUEST_SIZE, Sort.Direction.DESC,
            this.paginationDirectionProperties());
  }

  @Override
  public void findGame(long teamId, long latitude, long longitude) {
    final Team team = this.teamService.get(teamId);
    final FindMatch findMatch = new FindMatch(team);
    findMatch.setLatitude(latitude);
    findMatch.setLongitude(longitude);
    this.findMatchPoolService.enter(findMatch);
  }

  @Override
  public void startGame(long teamId) {
    final Team team = this.teamService.get(teamId);
    this.validateTeamInPool(confirmingGamePoolService, team);
    this.confirmingGamePoolService.teamConfirm(team);
  }

  @Override
  public void endGame(TeamAssertResult teamAssertResult) {
    final Team team = this.teamService.get(teamAssertResult.getTeam().getId());
    this.validateTeamInPool(onGoingGamePoolService, team);
    this.onGoingGamePoolService.teamAssertGameEnd(teamAssertResult);
  }

  private void validateTeamInPool(PoolService poolService, Team team) {
    final boolean teamInPool = poolService.teamInPool(team);
    if (!teamInPool) {
      throw new KyrieException(KyrieExceptionCode.TEAM_NOT_IN_POOL);
    }
  }

  protected Specification<Game> isTeam1IdEqual(long teamId) {
    return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("team1").get("id"), teamId);
  }

  protected Specification<Game> isTeam2IdEqual(long teamId) {
    return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("team2").get("id"), teamId);
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
