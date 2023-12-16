package mo.bitcode.kyrie.service.team.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mo.bitcode.kyrie.common.model.KyrieTableBase;
import mo.bitcode.kyrie.service.user_profile.model.KyrieUserProfile;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Team extends KyrieTableBase {

  private int rating;
  private int season;
  private TeamType teamType;
  @OneToMany(cascade = CascadeType.REFRESH)
  private List<KyrieUserProfile> userProfiles;

}
