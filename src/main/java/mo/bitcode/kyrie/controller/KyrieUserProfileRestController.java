package mo.bitcode.kyrie.controller;

import mo.bitcode.core.controller.user_profile.UserProfileRestController;
import mo.bitcode.core.service.user_profile.UserProfileService;
import mo.bitcode.kyrie.service.user_profile.model.KyrieUserProfile;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KyrieUserProfileRestController extends UserProfileRestController<KyrieUserProfile> {

  public KyrieUserProfileRestController(UserProfileService<KyrieUserProfile> userProfileService) {
    super(userProfileService);
  }

}
