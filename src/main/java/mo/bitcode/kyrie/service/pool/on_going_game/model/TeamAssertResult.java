package mo.bitcode.kyrie.service.pool.on_going_game.model;

import lombok.Data;
import mo.bitcode.kyrie.service.team.model.Team;

@Data
public class TeamAssertResult {

  private short assertScore;
  private Team team;

}
