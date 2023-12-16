package mo.bitcode.kyrie.service.application_config.impl;

import mo.bitcode.kyrie.service.application_config.ApplicationConfigServiceTemplate;
import mo.bitcode.kyrie.service.application_config.model.ApplicationConfig;
import mo.bitcode.kyrie.service.application_config.model.TeamConfig;
import org.springframework.stereotype.Service;

@Service
public class MemoryCacheApplicationConfigService extends ApplicationConfigServiceTemplate {

  private static final int CREATE_TEAM_RATING = 0;
  private static final int CREATE_TEAM_SEASON = 1;

  @Override
  public ApplicationConfig getApplicationConfig() {
    final TeamConfig teamConfig = TeamConfig.builder()
            .createTeamDefaultRating(CREATE_TEAM_RATING)
            .build();
    return ApplicationConfig.builder()
            .season(CREATE_TEAM_SEASON)
            .teamConfig(teamConfig)
            .build();
  }

  @Override
  public void updateApplicationConfig(ApplicationConfig applicationConfig) {

  }

}
