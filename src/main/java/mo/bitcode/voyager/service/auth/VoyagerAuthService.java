package mo.bitcode.voyager.service.auth;

import mo.bitcode.voyager.service.user_profile.model.VoyagerUserProfile;
import mo.bitcode.sms.sms_auth.SmsAuthService;

public interface VoyagerAuthService extends SmsAuthService<VoyagerUserProfile> {
  
}
