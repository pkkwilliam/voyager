package mo.bitcode.kyrie.service.notify;

import mo.bitcode.kyrie.service.game.model.Game;
import mo.bitcode.kyrie.service.team.model.Team;

public interface NotifyService {

  void notifyGameConfirmed(Team team);
  void notifyGameDisputed(Game game);
  void notifyGameMatched(Team team);

}
