package mo.bitcode.voyager.service.user_profile.model;

import mo.bitcode.core.common.UserProfile;
import mo.bitcode.core.security.model.UserRole;

import javax.persistence.Entity;

@Entity
public class VoyagerUserProfile extends UserProfile<UserRole> {

}
