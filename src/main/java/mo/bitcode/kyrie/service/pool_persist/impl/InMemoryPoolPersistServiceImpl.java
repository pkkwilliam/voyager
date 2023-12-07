package mo.bitcode.kyrie.service.pool_persist.impl;

import mo.bitcode.core.cache.CacheService;
import mo.bitcode.core.cache.impl.HashMapCacheService;
import mo.bitcode.kyrie.service.pool_persist.PoolPersistServiceAttributes;
import mo.bitcode.kyrie.service.pool_persist.PoolPersistServiceTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("inMemoryPoolPersistServiceImpl")
public class InMemoryPoolPersistServiceImpl<ID, T extends PoolPersistServiceAttributes<ID>>
        extends PoolPersistServiceTemplate<ID, T> {

  private CacheService<ID, T> cacheService;
  private Set<ID> poolIds;

  public InMemoryPoolPersistServiceImpl() {
    this.cacheService = new HashMapCacheService<>();
    this.poolIds = new HashSet<>();
  }

  @Override
  public void enterPool(T t) {
    this.cacheService.save(t.getId(), t);
    this.poolIds.add(t.getId());
  }

  @Override
  public T get(ID id) {
    return this.cacheService.get(id);
  }

  @Override
  public List<T> getAll() {
    return this.poolIds.stream()
            .map(id -> this.cacheService.get(id))
            .collect(Collectors.toList());
  }

  @Override
  public void remove(ID id) {
    this.cacheService.remove(id);
    this.poolIds.remove(id);
  }

  @Override
  public void update(T t) {
    this.cacheService.save(t.getId(), t);
  }

}
