package mo.bitcode.voyager.service.auth.impl;

import mo.bitcode.core.security.jwt.JwtService;
import mo.bitcode.voyager.service.auth.VoyagerAuthServiceTemplate;
import mo.bitcode.voyager.service.user_profile.VoyagerUserProfileService;
import mo.bitcode.voyager.service.user_profile.model.VoyagerUserProfile;
import mo.bitcode.sms.provider.SmsProviderService;
import mo.bitcode.sms.repository.PoormanVerificationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class VoyagerAuthServiceImpl extends VoyagerAuthServiceTemplate {

  public VoyagerAuthServiceImpl(JwtService<VoyagerUserProfile> jwtService,
                                @Value("${mock.sms.message}") boolean mockSmsService,
                                SmsProviderService smsProviderService,
                                VoyagerUserProfileService voyagerUserProfileService,
                                PoormanVerificationRepository poormanVerificationJpaRepository) {
    super(jwtService, mockSmsService, smsProviderService, voyagerUserProfileService, poormanVerificationJpaRepository);
  }

}
