package com.decipherzone.oauthsample.core.exception;

import com.decipherzone.oauthsample.web.core.rest.ResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AccessDeniedException extends BaseRestException {

  public AccessDeniedException(ResponseCode responseCode) {
    super(responseCode);
  }

  public AccessDeniedException(String message, ResponseCode responseCode) {
    super(message, responseCode);
  }

  public AccessDeniedException(String message, Throwable cause, ResponseCode responseCode) {
    super(message, cause, responseCode);
  }

  public AccessDeniedException(Throwable cause, ResponseCode responseCode) {
    super(cause, responseCode);
  }

  public AccessDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
                               ResponseCode responseCode) {
    super(message, cause, enableSuppression, writableStackTrace, responseCode);
  }
}
