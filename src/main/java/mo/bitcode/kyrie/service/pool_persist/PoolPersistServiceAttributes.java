package mo.bitcode.kyrie.service.pool_persist;

import java.time.OffsetDateTime;

public interface PoolPersistServiceAttributes<ID> {

  OffsetDateTime getEnterPoolTimestamp();
  ID getId();

}
