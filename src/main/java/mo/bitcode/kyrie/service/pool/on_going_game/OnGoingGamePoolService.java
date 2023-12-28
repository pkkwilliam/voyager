package mo.bitcode.kyrie.service.pool.on_going_game;

import mo.bitcode.kyrie.service.pool.PoolService;
import mo.bitcode.kyrie.service.pool.on_going_game.model.OnGoingGame;
import mo.bitcode.kyrie.service.pool.on_going_game.model.TeamAssertResult;

public interface OnGoingGamePoolService extends PoolService<OnGoingGame> {

  void teamAssertGameEnd(TeamAssertResult teamAssertResult);

}
