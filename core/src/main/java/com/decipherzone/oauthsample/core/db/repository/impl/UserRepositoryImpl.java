package com.decipherzone.oauthsample.core.db.repository.impl;

import com.decipherzone.oauthsample.core.db.domain.User;
import com.decipherzone.oauthsample.core.db.repository.UserRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class UserRepositoryImpl extends BaseRepository<User> implements UserRepository {

}
