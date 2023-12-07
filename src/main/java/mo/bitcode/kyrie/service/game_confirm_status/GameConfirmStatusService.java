package mo.bitcode.kyrie.service.game_confirm_status;

import mo.bitcode.kyrie.service.game_confirm_status.model.GameConfirmStatus;

public interface GameConfirmStatusService {

  void confirmGame(long gameId, GameConfirmStatus gameConfirmStatus);

}
