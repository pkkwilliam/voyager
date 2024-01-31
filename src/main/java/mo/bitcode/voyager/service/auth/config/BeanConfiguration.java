package mo.bitcode.voyager.service.auth.config;

import mo.bitcode.core.security.AppSecurityConfig;
import mo.bitcode.core.security.impl.AppSecurityConfigImpl;
import mo.bitcode.core.security.jwt.JwtService;
import mo.bitcode.core.security.jwt.impl.JwtServiceImplBasic;
import mo.bitcode.voyager.service.user_profile.VoyagerUserProfileServiceTemplate;
import mo.bitcode.voyager.service.user_profile.model.VoyagerUserProfile;
import mo.bitcode.sms.provider.SmsProviderService;
import mo.bitcode.sms.provider.twilio.TwilioSmsProviderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

  @Bean
  public JwtService<VoyagerUserProfile> getJwtService(@Value("${jwt.token.expiration}") long expiration,
                                                      @Value("${jwt.token.secret}") String secret) {
    return new JwtServiceImplBasic<>(expiration, secret);
  }

  @Bean
  public AppSecurityConfig getAppSecurityConfig(JwtService<VoyagerUserProfile> jwtService, VoyagerUserProfileServiceTemplate kyrieUserProfileServiceTemplate) {
    return new AppSecurityConfigImpl(jwtService, kyrieUserProfileServiceTemplate);
  }

  @Bean("twilioSmsProvider")
  public SmsProviderService getTencentSmsProvider(@Value("${twilio.account.sid}") String accountSid,
                                                  @Value("${twilio.auth.token}") String token,
                                                  @Value("${twilio.sms.fromNumber}") String fromNumber,
                                                  @Value("${twilio.verification.sid}") String verificationSid) {
    return new TwilioSmsProviderService(accountSid, token, fromNumber, verificationSid);
  }

}
