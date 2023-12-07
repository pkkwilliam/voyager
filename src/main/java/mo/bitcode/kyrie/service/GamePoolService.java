package mo.bitcode.kyrie.service;

import mo.bitcode.kyrie.service.game.model.Game;
import mo.bitcode.kyrie.service.team.model.Team;

public interface GamePoolService {

  // ProcessingPool
  Game findGameInProcessingPool(Team team);
  void addGameToProcessingPool(Game game);
  void removeGameFromProcessingPool(Game game);

  // Ready Pool
  void addGameToReadyPool(Game game);
  void removeGameFromReadyPool(Game game);

  // Pending Match Pool
  void addTeamToFindMatchPool(Team team);
  void removeTeamFromFindMatchPool(Team team);

}
