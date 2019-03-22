package com.decipherzone.oauthsample.core.exception;

public class BaseInternalException extends BaseException {
  public BaseInternalException() {
  }

  public BaseInternalException(String message) {
    super(message);
  }

  public BaseInternalException(String message, Throwable cause) {
    super(message, cause);
  }

  public BaseInternalException(Throwable cause) {
    super(cause);
  }

  public BaseInternalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
