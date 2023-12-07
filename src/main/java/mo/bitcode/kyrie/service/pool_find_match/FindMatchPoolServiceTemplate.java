package mo.bitcode.kyrie.service.pool_find_match;

import mo.bitcode.kyrie.common.model.GeoLocationAttributes;
import mo.bitcode.kyrie.service.geo_location.GeoLocationProximityService;
import mo.bitcode.kyrie.service.pool_find_match.model.FindMatchVo;
import mo.bitcode.kyrie.service.pool_persist.PoolPersistService;
import mo.bitcode.kyrie.service.team.TeamService;
import mo.bitcode.kyrie.service.team.model.Team;

import java.time.OffsetDateTime;
import java.util.List;

public class FindMatchPoolServiceTemplate implements FindMatchPoolService {

  private GeoLocationProximityService geoLocationProximityService;
  private PoolPersistService<Long, FindMatchVo> poolPersistService;

  public FindMatchPoolServiceTemplate(GeoLocationProximityService geoLocationProximityService,
                                      PoolPersistService<Long, FindMatchVo> poolPersistService) {
    this.geoLocationProximityService = geoLocationProximityService;
    this.poolPersistService = poolPersistService;
  }

  @Override
  public FindMatchVo getInfo(long teamId) {
    return this.poolPersistService.get(teamId);
  }

  @Override
  public List<FindMatchVo> findMatch(long teamId, FindMatchVo current, int withIn) {
    final FindMatchVo currentTeamVo = poolPersistService.get(teamId);
    final List<FindMatchVo> pendingMatchVos = this.poolPersistService.getAll();
    final List<FindMatchVo> nearByCandidates =
            this.geoLocationProximityService.getNearBy(currentTeamVo, pendingMatchVos, withIn);
    return nearByCandidates;
  }

  @Override
  public void enterPool(FindMatchVo findMatchVo) {
    findMatchVo.setEnterPoolTimestamp(OffsetDateTime.now());
    this.poolPersistService.enterPool(findMatchVo);
  }

  @Override
  public void quitPool(long teamId) {
    this.poolPersistService.remove(teamId);
  }

}
