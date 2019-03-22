package com.decipherzone.oauthsample.core.service;

import com.decipherzone.oauthsample.core.db.domain.User;

public interface SessionService {

  User getLoggedInUser();

}
