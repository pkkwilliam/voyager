package mo.bitcode.kyrie.service.team.impl;

import mo.bitcode.kyrie.service.team.TeamRepository;
import mo.bitcode.kyrie.service.team.TeamServiceTemplate;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl extends TeamServiceTemplate {

  public TeamServiceImpl(TeamRepository teamRepository) {
    super(teamRepository);
  }

}
