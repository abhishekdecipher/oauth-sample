package com.decipherzone.oauthsample.core.db.domain;

import io.swagger.annotations.ApiModelProperty;

import java.util.UUID;

public class UUIDEnabledModel extends DeleteableModel {

    @ApiModelProperty(notes = "Auto-generated")
    protected String uuid = UUID.randomUUID().toString();

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
