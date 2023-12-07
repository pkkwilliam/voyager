package mo.bitcode.kyrie.common.exception;

import mo.bitcode.core.exception.ApplicationException;

public class KyrieException extends ApplicationException {

  public KyrieException(KyrieExceptionCode kyrieExceptionCode) {
    super(kyrieExceptionCode.getHttpStatus(), kyrieExceptionCode.getErrorCode(), kyrieExceptionCode.getMessage());
  }

}
