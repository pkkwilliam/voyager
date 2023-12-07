package mo.bitcode.kyrie.service.rating.model;

import lombok.Data;
import mo.bitcode.kyrie.common.model.KyrieTableBase;
import mo.bitcode.kyrie.service.team.model.Team;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@Entity
public class Rating extends KyrieTableBase {

  private int rating;
  private int season;

  @OneToOne
  private Team team;

}
