package com.decipherzone.oauthsample.web.core.rest.controller;

import com.decipherzone.oauthsample.core.db.domain.TimestampableModel;
import com.decipherzone.oauthsample.core.db.repository.Repository;
import com.decipherzone.oauthsample.core.service.Service;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractBaseRestController<E extends TimestampableModel, S extends Service<E, R>, R extends Repository<E>>
  implements RestController<E, S, R> {

  @Autowired
  protected S service;

}
