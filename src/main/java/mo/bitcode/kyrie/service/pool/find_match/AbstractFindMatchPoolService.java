package mo.bitcode.kyrie.service.pool.find_match;

import mo.bitcode.kyrie.common.model.GeoLocationAttributes;
import mo.bitcode.kyrie.service.notify.NotifyService;
import mo.bitcode.kyrie.service.pool.confirming_game.ConfirmingGamePoolService;
import mo.bitcode.kyrie.service.pool.confirming_game.model.ConfirmingGame;
import mo.bitcode.kyrie.service.pool.find_match.model.FindMatch;
import mo.bitcode.kyrie.service.team.model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFindMatchPoolService implements FindMatchPoolService {

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractFindMatchPoolService.class);

  private ConfirmingGamePoolService confirmingGamePoolService;
  private NotifyService notifyService;

  public AbstractFindMatchPoolService(ConfirmingGamePoolService confirmingGamePoolService, NotifyService notifyService) {
    this.confirmingGamePoolService = confirmingGamePoolService;
    this.notifyService = notifyService;
  }

  protected abstract void enterImplementation(FindMatch findMatch);

  @Override
  public void enter(FindMatch findMatch) {
    final List<FindMatch> nearBys = this.findNearBy(findMatch);
    if (nearBys == null || nearBys.isEmpty()) {
      LOGGER.debug("no near by existed, adding into pool");
      this.enterImplementation(findMatch);
      return;
    }
    final FindMatch matched = this.findClosestRating(findMatch, nearBys);
    this.onGameMatched(findMatch, matched);
  }

  protected List<FindMatch> findNearBy(GeoLocationAttributes geoLocationAttributes) {
    // TODO need implementation
    return new ArrayList<>();
  }

  protected void onGameMatched(FindMatch firstCome, FindMatch laterCome) {
    final ConfirmingGame confirmingGame = new ConfirmingGame(firstCome.getTeam(), laterCome.getTeam());
    this.confirmingGamePoolService.enter(confirmingGame);
    this.quit(firstCome.getId());
    this.quit(laterCome.getId());
    for (Team team: confirmingGame.getTeams()) {
      this.notifyService.notifyGameMatched(team);
    }
  }

  protected FindMatch findClosestRating(FindMatch findMatch, List<FindMatch> nearBys) {
    FindMatch result = null;
    int difference = Integer.MAX_VALUE;
    for (FindMatch candidate: nearBys) {
      final int localDifference = Math.abs(findMatch.getTeam().getRating() - candidate.getTeam().getRating());
      if (result == null || localDifference < difference) {
        result = candidate;
        difference = localDifference;
      }
    }
    return result;
  }

}
