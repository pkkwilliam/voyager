package mo.bitcode.kyrie.service.team.model;

import mo.bitcode.core.service.rest_template.query.model.QueryRequestVo;

public class TeamQueryRequest extends Team implements QueryRequestVo {

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
