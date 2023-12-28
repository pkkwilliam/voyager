package mo.bitcode.kyrie.service.pool.on_going_game.model;

import lombok.Getter;
import lombok.Setter;
import mo.bitcode.kyrie.service.pool.model.MatchedAttributeVo;
import mo.bitcode.kyrie.service.team.model.Team;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class OnGoingGame extends MatchedAttributeVo {

  private OffsetDateTime startTime;
  private TeamAssertResult[] teamAssertResults;

  public OnGoingGame(List<Team> teams) {
    this(teams.get(0), teams.get(1));
  }

  public OnGoingGame(Team team1, Team team2) {
    super(team1, team2);
    this.teamAssertResults = new TeamAssertResult[2];
  }

}
