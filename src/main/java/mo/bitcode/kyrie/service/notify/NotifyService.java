package mo.bitcode.kyrie.service.notify;

import mo.bitcode.kyrie.service.game.model.Game;

public interface NotifyService {

  void notifyGameReady(Game game);
  void notifyStartGame(Game game);

}
