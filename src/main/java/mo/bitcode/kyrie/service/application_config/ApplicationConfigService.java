package mo.bitcode.kyrie.service.application_config;

import mo.bitcode.kyrie.service.application_config.model.ApplicationConfig;

public interface ApplicationConfigService {

  ApplicationConfig getApplicationConfig();
  void updateApplicationConfig(ApplicationConfig applicationConfig);

}
