package mo.bitcode.kyrie.service.user_profile;

import mo.bitcode.kyrie.service.user_profile.model.KyrieUserProfile;
import mo.bitcode.sms.sms_userprofile.SmsUserProfileService;

public interface KyrieUserProfileService extends SmsUserProfileService<KyrieUserProfile> {

  KyrieUserProfile onUserRegister(KyrieUserProfile kyrieUserProfile);

}
