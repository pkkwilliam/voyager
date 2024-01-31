package mo.bitcode.voyager.controller;

import mo.bitcode.core.controller.user_profile.UserProfileRestController;
import mo.bitcode.core.service.user_profile.UserProfileService;
import mo.bitcode.voyager.service.user_profile.model.VoyagerUserProfile;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoyagerUserProfileRestController extends UserProfileRestController<VoyagerUserProfile> {

  public VoyagerUserProfileRestController(UserProfileService<VoyagerUserProfile> userProfileService) {
    super(userProfileService);
  }

}
