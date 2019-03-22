package com.decipherzone.oauthsample.core.db.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Token extends UUIDEnabledModel {

  private long expiry;

  private String userid;

  private transient User user;

  private Type type;

  private boolean used;

  public long getExpiry() {
    return expiry;
  }

  public void setExpiry(long expiry) {
    this.expiry = expiry;
  }

  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object obj) {

    if (obj == null /*|| !this.getClass() instanceof obj.getClass()*/) {
      return false;
    }

    Token that = (Token) obj;

    if (that.getCreatedDate() != this.getCreatedDate()) {
      return false;
    }

    if (that.uuid == null) {
      if (this.uuid != null)
        return false;
    } else if (!that.uuid.equals(this.uuid))
      return false;

    if (that.userid == null) {
      if (this.userid != null)
        return false;
    } else if (!that.userid.equals(this.userid))
      return false;

    if (that.type == null) {
      if (this.type != null)
        return false;
    } else if (!that.type.equals(this.type))
      return false;

    if (that.used != that.used) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hash = 31;
    hash = 31 * hash + Long.hashCode(this.getCreatedDate());
    hash = 31 * hash + (this.uuid != null ? this.uuid.hashCode() : 0);
    hash = 31 * hash + (this.userid != null ? this.userid.hashCode() : 0);
    hash = 31 * hash + (this.type != null ? this.type.hashCode() : 0);
    hash = 31 * hash + Boolean.hashCode(used);
    return hash;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public boolean isUsed() {
    return used;
  }

  public void setUsed(boolean used) {
    this.used = used;
  }

  public enum Type {
    AUTH
  }
}
