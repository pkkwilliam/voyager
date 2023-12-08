package mo.bitcode.kyrie.service.team;

import mo.bitcode.kyrie.service.team.model.Team;

public interface TeamService {

  Team get(long id);
  Team create(Team team);

}
