package com.decipherzone.oauthsample.web.core.rest.controller;

import com.decipherzone.oauthsample.core.db.domain.IdentityModel;
import com.decipherzone.oauthsample.core.db.repository.Repository;
import com.decipherzone.oauthsample.core.service.Service;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public abstract class AbstractBaseCrudRestController<E extends IdentityModel, S extends Service<E, R>, R extends Repository<E>>
  extends AbstractBaseRestController<E, S, R> {

  @GetMapping(value = "/{id}")
  @ApiOperation(value = "Get model by ID")
  public E findById(@PathVariable String id) {
    return this.service.findById(id);
  }

  @GetMapping
  @ApiOperation(value = "Get all non deleted models")
  public List<E> findAll() {
    return this.service.findAll();
  }

  @PostMapping
  @ApiOperation(value = "Create new model")
  public E create(@RequestBody E model) {
    return this.service.save(model);
  }

  @PutMapping
  @ApiOperation(value = "Update model")
  public E update(@RequestBody E model) {
    return this.service.update(model);
  }


  @DeleteMapping(value = "/{id}")
  @ApiOperation(value = "Delete model by ID")
  public void delete(@PathVariable String id) {
    this.service.delete(id);
  }
}
