package mo.bitcode.kyrie.service.pool_find_match.model;

import lombok.Builder;
import lombok.Data;
import mo.bitcode.kyrie.common.model.GeoLocationAttributes;
import mo.bitcode.kyrie.service.pool_persist.PoolPersistServiceAttributes;
import mo.bitcode.kyrie.service.team.model.Team;

import java.time.OffsetDateTime;

@Builder
@Data
public class FindMatchVo implements PoolPersistServiceAttributes<Long>, GeoLocationAttributes {

  private long distance;
  private OffsetDateTime enterPoolTimestamp;
  private long latitude;
  private long longitude;
  private Team team;

  @Override
  public Long getId() {
    return team.getId();
  }

}
