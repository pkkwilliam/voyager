package mo.bitcode.kyrie.service.game.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mo.bitcode.kyrie.common.model.KyrieTableBase;
import mo.bitcode.kyrie.service.team.model.Team;
import mo.bitcode.kyrie.service.team.model.TeamType;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game extends KyrieTableBase {

  @OneToOne
  private Team team1;
  @OneToOne
  private Team team2;
  private TeamType teamType;
  private short team1Score;
  private short team2Score;

}
