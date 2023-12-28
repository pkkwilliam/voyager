package mo.bitcode.kyrie.service.pool.find_match.impl;

import mo.bitcode.core.cache.CacheService;
import mo.bitcode.core.cache.impl.HashMapCacheService;
import mo.bitcode.kyrie.service.notify.NotifyService;
import mo.bitcode.kyrie.service.pool.confirming_game.ConfirmingGamePoolService;
import mo.bitcode.kyrie.service.pool.find_match.AbstractFindMatchPoolService;
import mo.bitcode.kyrie.service.pool.find_match.model.FindMatch;
import mo.bitcode.kyrie.service.team.model.Team;

public class MemoryCacheFindMatchPoolService extends AbstractFindMatchPoolService {

  private CacheService<String, FindMatch> cacheService;
  private ConfirmingGamePoolService confirmingGamePoolService;

  public MemoryCacheFindMatchPoolService(NotifyService notifyService,
                                         ConfirmingGamePoolService confirmingGamePoolService) {
    super(confirmingGamePoolService, notifyService);
    this.cacheService = new HashMapCacheService();
    this.confirmingGamePoolService = confirmingGamePoolService;
  }

  @Override
  public FindMatch get(String id) {
    return this.cacheService.get(id);
  }

  @Override
  public boolean inPool(String id) {
    return cacheService.exists(id);
  }

  @Override
  public boolean teamInPool(Team team) {
    return false;
  }

  @Override
  public void quit(String id) {
    this.cacheService.remove(id);
  }

  @Override
  protected void enterImplementation(FindMatch findMatch) {
    this.cacheService.save(findMatch.getId(), findMatch);
  }

}
