package com.decipherzone.oauthsample.core.config.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class ApplicationProperties {

  @Autowired
  private DbProperties dbProperties;

  @Autowired
  private SecurityProperties securityProperties;

  public DbProperties getDbProperties() {
    return dbProperties;
  }

  public void setDbProperties(DbProperties dbProperties) {
    this.dbProperties = dbProperties;
  }

  public SecurityProperties getSecurityProperties() {
    return securityProperties;
  }

  public void setSecurityProperties(SecurityProperties securityProperties) {
    this.securityProperties = securityProperties;
  }
}
