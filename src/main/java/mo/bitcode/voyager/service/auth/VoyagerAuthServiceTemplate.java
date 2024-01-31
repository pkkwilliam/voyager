package mo.bitcode.voyager.service.auth;

import mo.bitcode.core.security.jwt.JwtService;
import mo.bitcode.voyager.service.user_profile.VoyagerUserProfileService;
import mo.bitcode.voyager.service.user_profile.model.VoyagerUserProfile;
import mo.bitcode.sms.provider.SmsProviderService;
import mo.bitcode.sms.repository.PoormanVerificationRepository;
import mo.bitcode.sms.sms_auth.SmsAuthServicePoormanVerificationTemplate;

public class VoyagerAuthServiceTemplate extends SmsAuthServicePoormanVerificationTemplate<VoyagerUserProfile> implements VoyagerAuthService {

  private VoyagerUserProfileService voyagerUserProfileService;

  public VoyagerAuthServiceTemplate(JwtService<VoyagerUserProfile> jwtService, boolean mockSmsService, SmsProviderService smsProviderService, VoyagerUserProfileService voyagerUserProfileService, PoormanVerificationRepository poormanVerificationRepository) {
    super(jwtService, mockSmsService, smsProviderService, voyagerUserProfileService, poormanVerificationRepository);
    this.voyagerUserProfileService = voyagerUserProfileService;
  }

  @Override
  protected void onUserProfileCreated(VoyagerUserProfile voyagerUserProfile) {
    this.voyagerUserProfileService.onUserRegister(voyagerUserProfile);
  }

}
