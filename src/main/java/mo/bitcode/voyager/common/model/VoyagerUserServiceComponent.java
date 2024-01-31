package mo.bitcode.voyager.common.model;

import mo.bitcode.core.common.ApplicationComponent;
import mo.bitcode.voyager.service.user_profile.VoyagerUserProfileService;
import mo.bitcode.voyager.service.user_profile.model.VoyagerUserProfile;

public class VoyagerUserServiceComponent extends ApplicationComponent {

  private VoyagerUserProfileService userProfileService;

  protected VoyagerUserProfile getUserProfile() {
    return this.userProfileService.getCurrentUserProfile();
  }

  protected VoyagerUserProfileService getUserProfileService() {
    return userProfileService;
  }

}
