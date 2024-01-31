package mo.bitcode.voyager.service.user_profile;

import mo.bitcode.core.repository.UserProfileRepository;
import mo.bitcode.core.security.model.UserRole;
import mo.bitcode.voyager.service.user_profile.model.VoyagerUserProfile;
import mo.bitcode.sms.sms_userprofile.SmsUserProfileServiceTemplate;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public abstract class VoyagerUserProfileServiceTemplate
        extends SmsUserProfileServiceTemplate<VoyagerUserProfile>
        implements VoyagerUserProfileService {


  public VoyagerUserProfileServiceTemplate(UserProfileRepository userProfileRepository) {
    super(userProfileRepository);
  }

  @Override
  protected VoyagerUserProfile generateEmptyApplicationProfileObject() {
    return new VoyagerUserProfile();
  }

  @Override
  protected GrantedAuthority getBasicUserRole() {
    return UserRole.ROLE_USER;
  }

  @Override
  protected String getSidPrefix() {
    return "voy";
  }

  @Override
  public VoyagerUserProfile onUserRegister(VoyagerUserProfile voyagerUserProfile) {
    return voyagerUserProfile;
  }

}
