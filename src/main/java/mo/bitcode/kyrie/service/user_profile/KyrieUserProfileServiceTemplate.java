package mo.bitcode.kyrie.service.user_profile;

import mo.bitcode.core.repository.UserProfileRepository;
import mo.bitcode.core.security.model.UserRole;
import mo.bitcode.kyrie.service.team.TeamService;
import mo.bitcode.kyrie.service.team.model.Team;
import mo.bitcode.kyrie.service.team.model.TeamType;
import mo.bitcode.kyrie.service.user_profile.model.KyrieUserProfile;
import mo.bitcode.sms.sms_userprofile.SmsUserProfileServiceTemplate;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public abstract class KyrieUserProfileServiceTemplate
        extends SmsUserProfileServiceTemplate<KyrieUserProfile>
        implements KyrieUserProfileService{

  private TeamService teamService;

  public KyrieUserProfileServiceTemplate(TeamService teamService, UserProfileRepository userProfileRepository) {
    super(userProfileRepository);
    this.teamService = teamService;
  }

  @Override
  protected KyrieUserProfile generateEmptyApplicationProfileObject() {
    return new KyrieUserProfile();
  }

  @Override
  protected GrantedAuthority getBasicUserRole() {
    return UserRole.ROLE_USER;
  }

  @Override
  protected String getSidPrefix() {
    return "K11";
  }

  @Override
  public KyrieUserProfile onUserRegister(KyrieUserProfile kyrieUserProfile) {
    final Team oneVsOneTeam = Team.builder()
            .teamType(TeamType.ONE_VS_ONE)
            .userProfiles(List.of(kyrieUserProfile))
            .build();
    this.teamService.create(oneVsOneTeam);
    return kyrieUserProfile;
  }

}
