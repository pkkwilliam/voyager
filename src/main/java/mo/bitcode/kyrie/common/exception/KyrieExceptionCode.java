package mo.bitcode.kyrie.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum KyrieExceptionCode {

  TEAM_NOT_ALLOW_TO_ENTER_POOL(HttpStatus.BAD_REQUEST, "400.001", "Team is already in other process.");

  private HttpStatus httpStatus;
  private String errorCode;
  private String message;

  KyrieExceptionCode(HttpStatus httpStatus, String errorCode, String message) {
    this.httpStatus = httpStatus;
    this.errorCode = errorCode;
    this.message = message;
  }

}
