package mo.bitcode.kyrie.service.user_profile;

import mo.bitcode.core.repository.UserProfileRepository;
import mo.bitcode.kyrie.service.user_profile.model.KyrieUserProfile;
import org.springframework.stereotype.Repository;

@Repository
public interface KyrieUserProfileRepository extends UserProfileRepository<KyrieUserProfile> {
}
