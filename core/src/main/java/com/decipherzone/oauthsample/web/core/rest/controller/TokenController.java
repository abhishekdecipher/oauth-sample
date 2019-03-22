package com.decipherzone.oauthsample.web.core.rest.controller;

import com.decipherzone.oauthsample.core.Constants;
import com.decipherzone.oauthsample.core.db.domain.Token;
import com.decipherzone.oauthsample.core.db.repository.TokenRepository;
import com.decipherzone.oauthsample.core.service.TokenService;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Public - Token", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(Constants.API_PUBLIC_BASE_URL + "/token")
public class TokenController extends AbstractBaseRestController<Token, TokenService, TokenRepository> {

  @GetMapping("/{type}/{token}")
  public ResponseEntity<Object> verify(@PathVariable Token.Type type, @PathVariable String token) {
    service.validateToken(type, token);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PutMapping("/{type}/{token}")
  public ResponseEntity<Object> verifyAndAct(@PathVariable Token.Type type, @PathVariable String token,
                                             @RequestBody JsonNode jsonNode) {
    service.act(type, token, jsonNode);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

}
