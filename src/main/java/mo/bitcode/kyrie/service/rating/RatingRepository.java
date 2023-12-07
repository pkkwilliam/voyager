package mo.bitcode.kyrie.service.rating;

import mo.bitcode.core.service.rest_template.query.AbstractQueryServiceRepository;
import mo.bitcode.kyrie.service.rating.model.Rating;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends AbstractQueryServiceRepository<Rating> {

  List<Rating> findByTeamIdIn(List<Long> teamIds);

}
