package mo.bitcode.kyrie.service.pool_find_match;

import mo.bitcode.kyrie.common.model.GeoLocationAttributes;
import mo.bitcode.kyrie.service.pool_find_match.model.FindMatchVo;

import java.util.List;

public interface FindMatchPoolService {

  FindMatchVo getInfo(long teamId);
  List<FindMatchVo> findMatch(long teamId, FindMatchVo current, int withIn);
  void enterPool(FindMatchVo findMatchVo);
  void quitPool(long teamId);

}
