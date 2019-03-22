package com.decipherzone.oauthsample.web.core.rest.response;

public class NonDataResponse {
  protected int code;
  protected String message = "";

  public NonDataResponse() {
  }

  public NonDataResponse(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }
}