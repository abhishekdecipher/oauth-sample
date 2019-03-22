package com.decipherzone.oauthsample.core.service;

import com.decipherzone.oauthsample.core.db.domain.User;
import com.decipherzone.oauthsample.core.db.repository.UserRepository;

public interface UserService extends Service<User, UserRepository> {

  User lookupByEmail(String email);

}
