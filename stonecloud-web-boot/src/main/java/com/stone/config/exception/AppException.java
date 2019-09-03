package com.stone.config.exception;

import org.apache.commons.lang.StringUtils;
import com.stone.enums.ErrorCode;

public class AppException extends RuntimeException {
  private static final long serialVersionUID = 8449738842423044010L;

  private ErrorCode code;

  public AppException(ErrorCode code) {
    this.code = code;
  }

  public AppException(ErrorCode code, String message) {
    super(message);
    this.code = code;
  }

  public ErrorCode getCode() {
    return code;
  }

  @Override
  public String getMessage() {
    if (StringUtils.isBlank(super.getMessage())) {
      return code.toString();
    }
    return super.getMessage();
  }
}
