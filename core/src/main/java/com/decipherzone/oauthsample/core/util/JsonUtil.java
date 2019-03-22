package com.decipherzone.oauthsample.core.util;

import com.decipherzone.oauthsample.core.exception.InitializationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public final class JsonUtil {

  private JsonUtil() {
  }

  public static String toJson(final Object object) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new InitializationException(e);
    }
  }

  public static <T> T fromJson(final String json, final Class<T> clazz) {
    final ObjectMapper objectMapper = new ObjectMapper();
    try {
      objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      return objectMapper.readValue(json, clazz);
    } catch (IOException e) {
      throw new InitializationException(e);
    }
  }

  public static <T> T fromJson(final JsonNode json, final Class<T> clazz) {
    final ObjectMapper objectMapper = new ObjectMapper();
    try {
      objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      return objectMapper.treeToValue(json, clazz);
    } catch (IOException e) {
      throw new InitializationException(e);
    }
  }
}
