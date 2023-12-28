package mo.bitcode.kyrie.service.pool.model;

import javax.persistence.MappedSuperclass;
import java.time.OffsetDateTime;
import java.util.UUID;

@MappedSuperclass
public class PoolServiceAttributeVo implements PoolServiceAttribute {

  private String id;
  private OffsetDateTime enterPoolTime;

  public PoolServiceAttributeVo() {
    this.id = UUID.randomUUID().toString();
    enterPoolTime = OffsetDateTime.now();
  }

  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public OffsetDateTime getEnterPoolTime() {
    return this.enterPoolTime;
  }

}
