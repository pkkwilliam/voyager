package mo.bitcode.kyrie.service.pool.confirming_game.model;

import lombok.Getter;
import lombok.Setter;
import mo.bitcode.kyrie.service.pool.model.MatchedAttributeVo;
import mo.bitcode.kyrie.service.team.model.Team;

@Getter
@Setter
public class ConfirmingGame extends MatchedAttributeVo {

  private Team[] confirmed;

  public ConfirmingGame(Team team1, Team team2) {
    super(team1, team2);
    confirmed = new Team[2];
  }

  public Team[] getConfirmed() {
    return confirmed;
  }

}
