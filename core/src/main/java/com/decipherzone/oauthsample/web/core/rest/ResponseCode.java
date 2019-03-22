package com.decipherzone.oauthsample.web.core.rest;

import org.springframework.http.HttpStatus;

public enum ResponseCode {

  ACCEPTED(200, HttpStatus.ACCEPTED),

  // 1000 - Errors for Authentication (401)
  UNAUTHORIZED(1001, HttpStatus.UNAUTHORIZED),

  // 1100 - Errors for Access Denied (403)
  ACCESS_DENIED(1100, HttpStatus.FORBIDDEN),
  SESSION_EXPIRED(1101, HttpStatus.FORBIDDEN),

  // 1200 - Errors for BadRequest (400)

  // 1300 - Errors for Not found (404)
  NO_DATA_FOUND(1301, HttpStatus.NOT_FOUND),

  // 1400 - Errors for Not Acceptable (406)

  // 1500 - Errors for Internal Server exceptions
  INTERNAL_SERVER_ERROR(1501, HttpStatus.INTERNAL_SERVER_ERROR);

  HttpStatus httpStatus;
  int code;

  ResponseCode(int code, HttpStatus status) {
    this.code = code;
    this.httpStatus = status;
  }

  public static ResponseCode valueOf(int code) {
    ResponseCode[] values = values();
    for (ResponseCode value : values) {
      if (value.code == code) {
        return value;
      }
    }
    throw new IllegalArgumentException("Value for this code is not available");
  }

  public int getCode() {
    return code;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }
}
