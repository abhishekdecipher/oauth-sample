package com.decipherzone.oauthsample.core.db.domain;

import io.swagger.annotations.ApiModelProperty;

public class TimestampableModel {

  @ApiModelProperty(notes = "Auto-generated")
  private long createdDate;

  @ApiModelProperty(notes = "Auto-generated")
  private long updatedDate;

  public long getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(long createdDate) {
    this.createdDate = createdDate;
  }

  public long getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(long updatedDate) {
    this.updatedDate = updatedDate;
  }
}
