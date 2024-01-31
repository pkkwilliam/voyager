package mo.bitcode.voyager.service.user_profile;

import mo.bitcode.voyager.service.user_profile.model.VoyagerUserProfile;
import mo.bitcode.sms.sms_userprofile.SmsUserProfileService;

public interface VoyagerUserProfileService extends SmsUserProfileService<VoyagerUserProfile> {

  VoyagerUserProfile onUserRegister(VoyagerUserProfile voyagerUserProfile);

}
