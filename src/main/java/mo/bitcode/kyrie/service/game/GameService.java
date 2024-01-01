package mo.bitcode.kyrie.service.game;

import mo.bitcode.core.service.rest_template.Service;
import mo.bitcode.kyrie.service.game.model.Game;
import mo.bitcode.kyrie.service.pool.on_going_game.model.TeamAssertResult;
import org.springframework.data.domain.Page;

public interface GameService extends Service<Game> {

  Page<Game> getByTeamId(long teamId, int pageRequest);

  void findGame(long teamId, long latitude, long longitude);

  void startGame(long teamId);

  void endGame(TeamAssertResult teamAssertResult);

}
