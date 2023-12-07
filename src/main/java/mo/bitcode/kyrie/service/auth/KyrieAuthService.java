package mo.bitcode.kyrie.service.auth;

import mo.bitcode.kyrie.service.user_profile.model.KyrieUserProfile;
import mo.bitcode.sms.sms_auth.SmsAuthService;

public interface KyrieAuthService extends SmsAuthService<KyrieUserProfile> {
  
}
