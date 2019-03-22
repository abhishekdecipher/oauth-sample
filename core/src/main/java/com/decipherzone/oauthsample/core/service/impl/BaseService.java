package com.decipherzone.oauthsample.core.service.impl;

import com.decipherzone.oauthsample.core.db.domain.TimestampableModel;
import com.decipherzone.oauthsample.core.db.repository.Repository;
import com.decipherzone.oauthsample.core.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public abstract class BaseService<E extends TimestampableModel, R extends Repository<E>> implements Service<E, R> {

  private static final Logger LOGGER = LogManager.getLogger(BaseService.class);

  protected R repository;

  public BaseService(R repository) {
    this.repository = repository;
  }

  @Override
  public E findById(String id) {
    return this.repository.findById(id);
  }

  @Override
  public List<E> findAll() {
    return this.repository.findAll();
  }

  @Override
  public Map<String, E> findAllAsMap() {
    return this.repository.findAllAsMap();
  }

  @Override
  public List<E> findAllBy(String column, String value) {
    return this.repository.findAllBy(column, value);
  }

  @Override
  public E findOneBy(String column, String value) {
    return this.repository.findOneBy(column, value);
  }

  @Override
  public void delete(E object) {
    this.repository.delete(object);
  }

  @Override
  public void delete(String id) {
    this.repository.delete(id);
  }

  @Override
  public E save(E e) {
    return this.repository.save(e);
  }

  @Override
  public E update(E e) {
    return this.repository.update(e);
  }
}
