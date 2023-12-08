package mo.bitcode.kyrie.service.team;

import mo.bitcode.core.service.rest_template.query.AbstractQueryServiceRepository;
import mo.bitcode.kyrie.service.team.model.Team;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends AbstractQueryServiceRepository<Team> {
}
