package mo.bitcode.voyager.common.model.my_story;

import lombok.Data;

@Data
public class Comment {

  private long id;
  private CommentType commentType;
  private long foreignId;

}
