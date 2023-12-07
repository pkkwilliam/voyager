package mo.bitcode.kyrie.service.rating;

import mo.bitcode.kyrie.service.game.model.Game;
import mo.bitcode.kyrie.service.team.model.Team;

public interface RatingService {

  void initTeamRating(Team team);
  void updateTeamRating(Game game);

}
