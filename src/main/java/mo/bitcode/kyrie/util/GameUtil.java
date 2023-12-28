package mo.bitcode.kyrie.util;

import mo.bitcode.kyrie.common.model.TeamSideWonResult;

public class GameUtil {

  public static TeamSideWonResult calculateTeamSideWonResult(short team1Score, short team2Score) {
    if (team1Score > team2Score) {
      return TeamSideWonResult.TEAM_1_WON;
    } else if (team2Score > team1Score) {
      return TeamSideWonResult.TEAM_2_WON;
    } else {
      return TeamSideWonResult.TIED;
    }
  }

}
