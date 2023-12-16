package mo.bitcode.kyrie.service.auth;

import mo.bitcode.core.security.jwt.JwtService;
import mo.bitcode.kyrie.service.user_profile.KyrieUserProfileService;
import mo.bitcode.kyrie.service.user_profile.model.KyrieUserProfile;
import mo.bitcode.sms.provider.SmsProviderService;
import mo.bitcode.sms.repository.PoormanVerificationRepository;
import mo.bitcode.sms.sms_auth.SmsAuthServicePoormanVerificationTemplate;

public class KyrieAuthServiceTemplate extends SmsAuthServicePoormanVerificationTemplate<KyrieUserProfile> implements KyrieAuthService {

  private KyrieUserProfileService kyrieUserProfileService;

  public KyrieAuthServiceTemplate(JwtService<KyrieUserProfile> jwtService, boolean mockSmsService, SmsProviderService smsProviderService, KyrieUserProfileService kyrieUserProfileService, PoormanVerificationRepository poormanVerificationRepository) {
    super(jwtService, mockSmsService, smsProviderService, kyrieUserProfileService, poormanVerificationRepository);
    this.kyrieUserProfileService = kyrieUserProfileService;
  }

  @Override
  protected void onUserProfileCreated(KyrieUserProfile kyrieUserProfile) {
    this.kyrieUserProfileService.onUserRegister(kyrieUserProfile);
  }

}
