package mo.bitcode.voyager.service.user_profile.impl;

import mo.bitcode.voyager.service.user_profile.VoyagerUserProfileRepository;
import mo.bitcode.voyager.service.user_profile.VoyagerUserProfileServiceTemplate;
import org.springframework.stereotype.Service;

@Service
public class VoyagerUserProfileServiceImpl extends VoyagerUserProfileServiceTemplate {


  public VoyagerUserProfileServiceImpl(VoyagerUserProfileRepository voyagerUserProfileRepository) {
    super(voyagerUserProfileRepository);
  }

}
