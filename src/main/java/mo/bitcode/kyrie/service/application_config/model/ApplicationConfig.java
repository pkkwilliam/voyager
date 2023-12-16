package mo.bitcode.kyrie.service.application_config.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApplicationConfig {

  private TeamConfig teamConfig;
  private int season;

}
