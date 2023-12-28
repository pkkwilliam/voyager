package mo.bitcode.kyrie.service.pool;

import mo.bitcode.kyrie.service.pool.model.PoolServiceAttribute;
import mo.bitcode.kyrie.service.team.model.Team;

public interface PoolService<T extends PoolServiceAttribute> {

  T get(String id);
  void enter(T t);
  boolean inPool(String id);
  boolean teamInPool(Team team);
  void quit(String id);

}
