package mo.bitcode.kyrie.service.pool.find_match.model;

import mo.bitcode.kyrie.common.model.GeoLocationAttributes;
import mo.bitcode.kyrie.service.pool.model.PoolServiceAttribute;
import mo.bitcode.kyrie.service.team.model.Team;

import java.time.OffsetDateTime;
import java.util.UUID;

public class FindMatch implements PoolServiceAttribute, GeoLocationAttributes {

  private String id;
  private OffsetDateTime enterPoolTime;
  private long latitude;
  private long longitude;
  private Team team;

  public FindMatch(Team team) {
    this.id = UUID.randomUUID().toString();
    this.enterPoolTime = OffsetDateTime.now();
    this.team = team;
  }

  public Team getTeam() {
    return team;
  }

  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public OffsetDateTime getEnterPoolTime() {
    return this.enterPoolTime;
  }

  @Override
  public long getLatitude() {
    return latitude;
  }

  public void setLatitude(long latitude) {
    this.latitude = latitude;
  }

  @Override
  public long getLongitude() {
    return longitude;
  }

  public void setLongitude(long longitude) {
    this.longitude = longitude;
  }
}
