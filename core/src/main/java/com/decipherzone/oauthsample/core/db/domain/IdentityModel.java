package com.decipherzone.oauthsample.core.db.domain;

import io.swagger.annotations.ApiModelProperty;

public class IdentityModel extends TimestampableModel {

  @ApiModelProperty(notes = "Auto-generated")
  private String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
