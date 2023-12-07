package mo.bitcode.kyrie.service.pool_persist;

import java.util.List;

public interface PoolPersistService<ID, T extends PoolPersistServiceAttributes<ID>> {

  void enterPool(T t);
  T get(ID id);
  List<T> getAll();
  void remove(ID id);
  void update(T t);

}
