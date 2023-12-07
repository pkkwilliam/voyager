package mo.bitcode.kyrie.service.auth.config;

import mo.bitcode.core.security.jwt.JwtService;
import mo.bitcode.core.security.jwt.impl.JwtServiceImplBasic;
import mo.bitcode.kyrie.service.user_profile.model.KyrieUserProfile;
import mo.bitcode.sms.provider.SmsProviderService;
import mo.bitcode.sms.provider.twilio.TwilioSmsProviderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

  @Bean
  public JwtService<KyrieUserProfile> getJwtService(@Value("${jwt.token.expiration}") long expiration,
                                                    @Value("${jwt.token.secret}") String secret) {
    return new JwtServiceImplBasic<>(expiration, secret);
  }

  @Bean("twilioSmsProvider")
  public SmsProviderService getTencentSmsProvider(@Value("${twilio.account.sid}") String accountSid,
                                                  @Value("${twilio.auth.token}") String token,
                                                  @Value("${twilio.sms.fromNumber}") String fromNumber,
                                                  @Value("${twilio.verification.sid}") String verificationSid) {
    return new TwilioSmsProviderService(accountSid, token, fromNumber, verificationSid);
  }

}
