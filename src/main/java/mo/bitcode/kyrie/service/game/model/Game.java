package mo.bitcode.kyrie.service.game.model;

import lombok.Data;
import mo.bitcode.kyrie.common.model.KyrieTableBase;
import mo.bitcode.kyrie.common.model.TeamType;
import mo.bitcode.kyrie.service.team.model.Team;

@Data
public class Game extends KyrieTableBase {

  private Team team1;
  private Team team2;
  private TeamType teamType;
  private short team1Score;
  private short team2Score;

}
