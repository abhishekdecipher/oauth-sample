package com.decipherzone.oauthsample.core.db.repository;

import com.decipherzone.oauthsample.core.db.domain.TimestampableModel;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface Repository<E extends TimestampableModel> extends Serializable {

  /**
   * Find by id
   *
   * @param id Primary key for entity
   * @return Reference of type E
   */
  E findById(String id);

  /**
   * Find by id incuding deleted
   *
   * @param id Primary key for entity
   * @return Reference of type E
   */
  E findIncludingDeletedById(String id);

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
   * @param e Entity<extends IdentityModel>
   */
  E save(E e);

  /**
   * Updates entity into DB
   *
   * @param e Entity<extends IdentityModel>
   */
  E update(E e);

  /**
   * Get all documents mapped as ID and Document
   *
   * @param items
   * @return Map of documents
   */
  Map<String, E> asMap(Iterable<E> items);

}
