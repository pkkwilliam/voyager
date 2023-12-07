package mo.bitcode.kyrie.service.user_profile;

import mo.bitcode.core.repository.UserProfileRepository;
import mo.bitcode.core.security.model.UserRole;
import mo.bitcode.kyrie.service.user_profile.model.KyrieUserProfile;
import mo.bitcode.sms.sms_userprofile.SmsUserProfileServiceTemplate;
import org.springframework.security.core.GrantedAuthority;

public abstract class KyrieUserProfileServiceTemplate
        extends SmsUserProfileServiceTemplate<KyrieUserProfile>
        implements KyrieUserProfileService{

  public KyrieUserProfileServiceTemplate(UserProfileRepository userProfileRepository) {
    super(userProfileRepository);
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
    // TODO create teams
    return kyrieUserProfile;
  }

}
