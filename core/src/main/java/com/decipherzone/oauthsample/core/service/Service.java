package com.decipherzone.oauthsample.core.service;

import com.decipherzone.oauthsample.core.db.domain.TimestampableModel;
import com.decipherzone.oauthsample.core.db.repository.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface Service<E extends TimestampableModel, R extends Repository<E>> extends Serializable {

  /**
   * Find by id
   *
   * @param id Primary key for entity
   * @return Reference of type E
   */
  E findById(String id);

  /**
   * Get all non deleted records
   *
   * @return List of E
   */
  List<E> findAll();

  /**
   * Get all documents mapped as ID and Document by column name and value
   *
   * @return Map of documents
   */
  Map<String, E> findAllAsMap();

  /**
   * Get list of rows by column and value
   *
   * @param column Column name
   * @param value  Column value
   * @return List<E> : Reference Type List
   */
  List<E> findAllBy(String column, String value);

  /**
   * Find by value
   *
   * @param column : String
   * @param value  : String
   * @return Reference of type E
   */
  E findOneBy(String column, String value);

  /**
   * Delete by object
   *
   * @param object E
   */
  void delete(E object);

  /**
   * Delete by primary key
   *
   * @param id Primary key of E
   */
  void delete(String id);

  /**
   * Persist entity into DB
   *
   * @param e Entity<extends TimestampableModel>
   */
  E save(E e);

  /**
   * Updates entity into DB
   *
   * @param e Entity<extends TimestampableModel>
   */
  E update(E e);
}
