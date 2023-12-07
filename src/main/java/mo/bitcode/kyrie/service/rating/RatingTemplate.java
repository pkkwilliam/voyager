package mo.bitcode.kyrie.service.rating;

import mo.bitcode.core.service.rest_template.query.AbstractQueryServiceTemplate;
import mo.bitcode.kyrie.service.game.model.Game;
import mo.bitcode.kyrie.service.rating.model.Rating;
import mo.bitcode.kyrie.service.rating.model.RatingQueryRequest;
import mo.bitcode.kyrie.service.team.model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class RatingTemplate
        extends AbstractQueryServiceTemplate<Rating, RatingQueryRequest>
        implements RatingService {

  private static final Logger LOGGER = LoggerFactory.getLogger(RatingTemplate.class);
  private static final int DEFAULT_INIT_RATING = 0;
  private static final int SEASON = 1;

  private RatingRepository ratingRepository;

  public RatingTemplate(RatingRepository ratingRepository) {
    super(ratingRepository);
    this.ratingRepository = ratingRepository;
  }

  protected abstract int calculateNewRating();

  @Override
  public void initTeamRating(Team team) {
    final Rating rating = new Rating();
    rating.setRating(DEFAULT_INIT_RATING);
    rating.setTeam(team);
    rating.setSeason(SEASON);
    this.create(rating);
  }

  @Override
  public void updateTeamRating(Game game) {
    if (game.getTeam1Score() == game.getTeam2Score()) {
      LOGGER.info("game both team score are the same, no updated required.");
    }
    final List<Long> teamIds = List.of(game.getTeam1().getId(), game.getTeam2().getId());
    final List<Rating> currentTeamRating = this.ratingRepository.findByTeamIdIn(teamIds);
    final Map<Team, Rating> teamRating = currentTeamRating.stream()
            .collect(Collectors.toMap(Rating::getTeam, Function.identity()));
    final boolean team1Won = game.getTeam1Score() > game.getTeam2Score();
    this.updateSingleTeam(game.getTeam1(), game.getTeam2(), teamRating.get(game.getTeam1()), team1Won);
    this.updateSingleTeam(game.getTeam2(), game.getTeam1(), teamRating.get(game.getTeam2()), !team1Won);
  }

  private void updateSingleTeam(Team team, Team oppositeTeam, Rating rating, boolean won) {
    final int updatedRating = this.calculateNewRating();
    rating.setRating(updatedRating);
    this.update(rating);
  }

  @Override
  protected Specification<Rating> generateBasicSpecification(RatingQueryRequest ratingQueryRequest) {
    return null;
  }

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }
}
