package com.decipherzone.oauthsample.core.exception;

import com.decipherzone.oauthsample.web.core.rest.ResponseCode;

public class BaseRestException extends BaseException {

  protected ResponseCode responseCode;

  public BaseRestException(ResponseCode responseCode) {
    this.responseCode = responseCode;
  }

  public BaseRestException(String message, ResponseCode responseCode) {
    super(message);
    this.responseCode = responseCode;
  }

  public BaseRestException(String message, Throwable cause, ResponseCode responseCode) {
    super(message, cause);
    this.responseCode = responseCode;
  }

  public BaseRestException(Throwable cause, ResponseCode responseCode) {
    super(cause);
    this.responseCode = responseCode;
  }

  public BaseRestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
                           ResponseCode responseCode) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.responseCode = responseCode;
  }

  public ResponseCode getResponseCode() {
    return responseCode;
  }
}
