package com.decipherzone.oauthsample.web.core.rest.controller;

import com.decipherzone.oauthsample.core.db.domain.TimestampableModel;
import com.decipherzone.oauthsample.core.db.repository.Repository;
import com.decipherzone.oauthsample.core.service.Service;

public interface RestController<E extends TimestampableModel, S extends Service<E, R>, R extends Repository<E>> {


}
