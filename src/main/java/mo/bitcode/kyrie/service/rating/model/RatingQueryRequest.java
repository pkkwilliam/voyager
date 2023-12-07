package mo.bitcode.kyrie.service.rating.model;

import mo.bitcode.core.service.rest_template.query.model.QueryRequestVo;

public class RatingQueryRequest extends Rating implements QueryRequestVo {
  @Override
  public String getCreateTimeRangeStart() {
    return null;
  }

  @Override
  public String getCreateTimeRangeEnd() {
    return null;
  }

  @Override
  public String getExcelExportFileName() {
    return null;
  }

  @Override
  public String getUpdateTimeRangeEnd() {
    return null;
  }

  @Override
  public String getUpdateTimeRangeStart() {
    return null;
  }
}
