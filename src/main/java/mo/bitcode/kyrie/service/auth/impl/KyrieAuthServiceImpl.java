package mo.bitcode.kyrie.service.auth.impl;

import mo.bitcode.core.security.jwt.JwtService;
import mo.bitcode.kyrie.service.auth.KyrieAuthServiceTemplate;
import mo.bitcode.kyrie.service.user_profile.KyrieUserProfileService;
import mo.bitcode.kyrie.service.user_profile.model.KyrieUserProfile;
import mo.bitcode.sms.provider.SmsProviderService;
import mo.bitcode.sms.repository.PoormanVerificationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KyrieAuthServiceImpl extends KyrieAuthServiceTemplate {

  public KyrieAuthServiceImpl(JwtService<KyrieUserProfile> jwtService,
                              @Value("${mock.sms.message}") boolean mockSmsService,
                              SmsProviderService smsProviderService,
                              KyrieUserProfileService kyrieUserProfileService,
                              PoormanVerificationRepository poormanVerificationJpaRepository) {
    super(jwtService, mockSmsService, smsProviderService, kyrieUserProfileService, poormanVerificationJpaRepository);
  }

}
