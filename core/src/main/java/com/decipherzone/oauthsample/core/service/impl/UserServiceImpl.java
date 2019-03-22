package com.decipherzone.oauthsample.core.service.impl;

import com.decipherzone.oauthsample.core.db.domain.User;
import com.decipherzone.oauthsample.core.db.repository.UserRepository;
import com.decipherzone.oauthsample.core.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Service
public class UserServiceImpl extends BaseService<User, UserRepository> implements UserService {

  private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

  private PasswordEncoder passwordEncoder;

  @Autowired
  public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
    super(repository);
    this.passwordEncoder = passwordEncoder;
  }

  @PostConstruct
  public void init() {
    if (Objects.isNull(this.lookupByEmail("admin@admin.com"))) {
      User user = new User();
      user.setFirstName("Admin");
      user.setLastName("Admin");
      user.setEmail("admin@admin.com");
      user.setMobileNumber("9876543210");
      user.setActive(Boolean.TRUE);
      user.setAccountLocked(Boolean.FALSE);
      user.setSecret(this.passwordEncoder.encode("admin"));
      this.save(user);
    }
  }

  @Override
  public User lookupByEmail(String email) {
    return this.repository.findOneBy("email", email);
  }
}
