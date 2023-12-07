package mo.bitcode.kyrie.service.user_profile.impl;

import mo.bitcode.kyrie.service.user_profile.KyrieUserProfileRepository;
import mo.bitcode.kyrie.service.user_profile.KyrieUserProfileServiceTemplate;
import org.springframework.stereotype.Service;

@Service
public class KyrieUserProfileServiceImpl extends KyrieUserProfileServiceTemplate {

  public KyrieUserProfileServiceImpl(KyrieUserProfileRepository kyrieUserProfileRepository) {
    super(kyrieUserProfileRepository);
  }

}
