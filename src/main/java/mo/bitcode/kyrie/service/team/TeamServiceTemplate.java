package mo.bitcode.kyrie.service.team;

import mo.bitcode.core.service.rest_template.query.AbstractQueryServiceRepository;
import mo.bitcode.core.service.rest_template.query.AbstractQueryServiceTemplate;
import mo.bitcode.kyrie.service.team.model.Team;
import mo.bitcode.kyrie.service.team.model.TeamQueryRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

public abstract class TeamServiceTemplate extends AbstractQueryServiceTemplate<Team, TeamQueryRequest> implements TeamService{

  private static final Logger LOGGER = LoggerFactory.getLogger(TeamServiceTemplate.class);
  private static final int CREATE_TEAM_RATING = 0;
  private static final int CREATE_TEAM_SEASON = 1;

  public TeamServiceTemplate(AbstractQueryServiceRepository<Team> abstractQueryServiceRepository) {
    super(abstractQueryServiceRepository);
  }

  @Override
  public Team create(Team requestChild) {
    requestChild.setRating(CREATE_TEAM_RATING);
    requestChild.setSeason(CREATE_TEAM_SEASON);
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
