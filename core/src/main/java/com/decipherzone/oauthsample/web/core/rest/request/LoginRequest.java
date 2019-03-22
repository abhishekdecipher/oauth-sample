package com.decipherzone.oauthsample.web.core.rest.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class LoginRequest {
  @NotNull
  @Email
  private String email;
  @NotNull
  private String secret;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }
}
