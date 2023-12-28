package mo.bitcode.kyrie.service.pool.model;

import lombok.Data;
import mo.bitcode.kyrie.service.team.model.Team;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class MatchedAttributeVo extends PoolServiceAttributeVo implements MatchedAttribute {

  private Team team1;
  private Team team2;

  public MatchedAttributeVo(Team team1, Team team2) {
    this.team1 = team1;
    this.team2 = team2;
  }

}
