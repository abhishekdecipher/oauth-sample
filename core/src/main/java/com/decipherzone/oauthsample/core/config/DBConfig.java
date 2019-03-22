package com.decipherzone.oauthsample.core.config;

import com.decipherzone.oauthsample.core.config.properties.ApplicationProperties;
import com.decipherzone.oauthsample.core.exception.InitializationException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteConcern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jongo.Jongo;
import org.jongo.marshall.jackson.JacksonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class DBConfig {

  private static final Logger LOGGER = LogManager.getLogger(DBConfig.class);
  private final ApplicationProperties applicationProperties;

  /**
   * @param applicationProperties
   */
  @Autowired
  public DBConfig(ApplicationProperties applicationProperties) {
    this.applicationProperties = applicationProperties;
  }

  /**
   * @return object of jongo class
   */
  @Bean
  public Jongo getDbConfig() {
    try {
      String uri = applicationProperties.getDbProperties().getUri();
      LOGGER.warn("uri : {}", uri);
      MongoClientURI clientURI = new MongoClientURI(uri);
      MongoClient client = new MongoClient(clientURI);
      DB db = new DB(client, client.getDatabase(Objects.requireNonNull(clientURI.getDatabase())).getName());
      db.setWriteConcern(WriteConcern.ACKNOWLEDGED);
      return new Jongo(db,
        new JacksonMapper.Builder()
          .registerModule(new JodaModule())
          .enable(MapperFeature.AUTO_DETECT_GETTERS)
          .build());
    } catch (Exception e) {
      throw new InitializationException(e.getMessage(), e);
    }
  }

}
