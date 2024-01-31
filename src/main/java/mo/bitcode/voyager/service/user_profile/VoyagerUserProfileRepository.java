package mo.bitcode.voyager.service.user_profile;

import mo.bitcode.core.repository.UserProfileRepository;
import mo.bitcode.voyager.service.user_profile.model.VoyagerUserProfile;
import org.springframework.stereotype.Repository;

@Repository
public interface VoyagerUserProfileRepository extends UserProfileRepository<VoyagerUserProfile> {
}
