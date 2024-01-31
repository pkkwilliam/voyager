package mo.bitcode.voyager.controller;

import mo.bitcode.sms.controller.SmsAuthRestController;
import mo.bitcode.sms.sms_auth.SmsAuthServiceTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoyagerSmsAuthRestController extends SmsAuthRestController {

  public VoyagerSmsAuthRestController(SmsAuthServiceTemplate smsAuthServiceTemplate) {
    super(smsAuthServiceTemplate);
  }

}
