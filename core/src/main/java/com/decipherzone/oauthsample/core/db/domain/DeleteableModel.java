package com.decipherzone.oauthsample.core.db.domain;

import io.swagger.annotations.ApiModelProperty;

public class DeleteableModel extends IdentityModel {

    @ApiModelProperty(notes = "Auto-generated")
    private Boolean deleted = Boolean.FALSE;

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
