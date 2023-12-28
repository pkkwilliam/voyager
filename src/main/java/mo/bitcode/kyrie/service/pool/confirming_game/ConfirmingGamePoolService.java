package mo.bitcode.kyrie.service.pool.confirming_game;

import mo.bitcode.kyrie.service.pool.PoolService;
import mo.bitcode.kyrie.service.pool.confirming_game.model.ConfirmingGame;
import mo.bitcode.kyrie.service.team.model.Team;

public interface ConfirmingGamePoolService extends PoolService<ConfirmingGame> {

  void teamConfirm(Team team);

}
