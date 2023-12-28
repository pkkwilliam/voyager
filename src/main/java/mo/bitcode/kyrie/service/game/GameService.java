package mo.bitcode.kyrie.service.game;

import mo.bitcode.core.service.rest_template.Service;
import mo.bitcode.kyrie.service.game.model.Game;
import mo.bitcode.kyrie.service.pool.on_going_game.model.TeamAssertResult;
import mo.bitcode.kyrie.service.team.model.Team;

public interface GameService extends Service<Game> {

  void findMatch(Team team, long latitude, long longitude);

  void startMatch(Team team);

  void endMatch(TeamAssertResult teamAssertResult);

}
