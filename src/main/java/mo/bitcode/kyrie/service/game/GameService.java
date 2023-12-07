package mo.bitcode.kyrie.service.game;

import mo.bitcode.kyrie.service.game.model.Game;
import mo.bitcode.kyrie.service.team.model.Team;

public interface GameService {

  void findMatch(Team team);

  void startMatch(Game game);

  void endMatch(Game game);

}
