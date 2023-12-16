package mo.bitcode.kyrie.service.team;

import mo.bitcode.core.service.rest_template.query.AbstractQueryServiceTemplate;
import mo.bitcode.kyrie.service.application_config.ApplicationConfigService;
import mo.bitcode.kyrie.service.application_config.model.ApplicationConfig;
import mo.bitcode.kyrie.service.team.model.Team;
import mo.bitcode.kyrie.service.team.model.TeamQueryRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

public abstract class TeamServiceTemplate extends AbstractQueryServiceTemplate<Team, TeamQueryRequest> implements TeamService {

  private static final Logger LOGGER = LoggerFactory.getLogger(TeamServiceTemplate.class);
  private ApplicationConfigService applicationConfigService;

  public TeamServiceTemplate(ApplicationConfigService applicationConfigService, TeamRepository teamRepository) {
    super(teamRepository);
    this.applicationConfigService = applicationConfigService;
  }

  @Override
  public Team create(Team requestChild) {
    final ApplicationConfig applicationConfig = this.applicationConfigService.getApplicationConfig();
    requestChild.setRating(applicationConfig.getTeamConfig().getCreateTeamDefaultRating());
    requestChild.setSeason(applicationConfig.getSeason());
    return super.create(requestChild);
  }

  @Override
  protected Specification<Team> generateBasicSpecification(TeamQueryRequest teamQueryRequest) {
    return null;
  }

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }

}
