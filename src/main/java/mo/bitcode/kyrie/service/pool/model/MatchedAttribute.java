package mo.bitcode.kyrie.service.pool.model;

import mo.bitcode.kyrie.common.exception.KyrieException;
import mo.bitcode.kyrie.common.exception.KyrieExceptionCode;
import mo.bitcode.kyrie.service.team.model.Team;

import java.util.List;

public interface MatchedAttribute {

  Team getTeam1();
  Team getTeam2();

  default List<Team> getTeams() {
    return List.of(getTeam1(), getTeam2());
  }

  default Team getTeam(Team team) {
    final int index = this.getTeamIndex(team);
    return this.getTeam(index);
  }

  default Team getTeam(int index) {
    return index == 0 ? getTeam1() : getTeam2();
  }

  default int getTeamIndex(Team team) {
    if (this.getTeam1().getId() == team.getId()) {
      return 0;
    } else if (this.getTeam2().getId() == team.getId()) {
      return 1;
    }
    throw new KyrieException(KyrieExceptionCode.TEAM_NOT_EXISTED);
  }

}
