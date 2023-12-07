package mo.bitcode.kyrie.common.model;

import mo.bitcode.core.common.table_base.TableBase;
import mo.bitcode.kyrie.service.user_profile.model.KyrieUserProfile;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class KyrieTableBase extends TableBase<KyrieUserProfile> {
}
