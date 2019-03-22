package com.decipherzone.oauthsample.core.config.properties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {

  private String encryptionSecret;

  private long authTokenExpiryInMS;

  private long inviteTokenExpiryInMS;

  public String getEncryptionSecret() {
    return encryptionSecret;
  }

  public void setEncryptionSecret(String encryptionSecret) {
    this.encryptionSecret = encryptionSecret;
  }

  public long getAuthTokenExpiryInMS() {
    return authTokenExpiryInMS;
  }

  public void setAuthTokenExpiryInMS(long authTokenExpiryInMS) {
    this.authTokenExpiryInMS = authTokenExpiryInMS;
  }

  public long getInviteTokenExpiryInMS() {
    return inviteTokenExpiryInMS;
  }

  public void setInviteTokenExpiryInMS(long inviteTokenExpiryInMS) {
    this.inviteTokenExpiryInMS = inviteTokenExpiryInMS;
  }
}
