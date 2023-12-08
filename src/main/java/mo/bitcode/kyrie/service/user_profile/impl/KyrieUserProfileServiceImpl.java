package mo.bitcode.kyrie.service.user_profile.impl;

import mo.bitcode.kyrie.service.team.TeamService;
import mo.bitcode.kyrie.service.user_profile.KyrieUserProfileRepository;
import mo.bitcode.kyrie.service.user_profile.KyrieUserProfileServiceTemplate;
import org.springframework.stereotype.Service;

@Service
public class KyrieUserProfileServiceImpl extends KyrieUserProfileServiceTemplate {


  public KyrieUserProfileServiceImpl(TeamService teamService, KyrieUserProfileRepository kyrieUserProfileRepository) {
    super(teamService, kyrieUserProfileRepository);
  }

}
