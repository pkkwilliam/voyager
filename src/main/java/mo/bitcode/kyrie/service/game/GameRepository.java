package mo.bitcode.kyrie.service.game;

import mo.bitcode.core.service.rest_template.query.QueryService;
import mo.bitcode.kyrie.service.game.model.Game;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends QueryService<Game> {
}
