package mo.bitcode.kyrie.controller;

import mo.bitcode.sms.controller.SmsAuthRestController;
import mo.bitcode.sms.sms_auth.SmsAuthServiceTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KyrieSmsAuthRestController extends SmsAuthRestController {

  public KyrieSmsAuthRestController(SmsAuthServiceTemplate smsAuthServiceTemplate) {
    super(smsAuthServiceTemplate);
  }

}
