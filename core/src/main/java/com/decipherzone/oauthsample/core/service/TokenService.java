package com.decipherzone.oauthsample.core.service;

import com.decipherzone.oauthsample.core.db.domain.Token;
import com.decipherzone.oauthsample.core.db.repository.TokenRepository;
import com.fasterxml.jackson.databind.JsonNode;

public interface TokenService extends Service<Token, TokenRepository> {

  boolean validateToken(Token.Type type, String tokenStr);

  void act(Token.Type type, String tokenStr, JsonNode jsonNode);

}
