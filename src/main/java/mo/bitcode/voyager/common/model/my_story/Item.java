package mo.bitcode.voyager.common.model.my_story;

import lombok.Data;

import java.util.List;

@Data
public class Item {

  private long id;
  private String content;
  private List<String> images;

}
