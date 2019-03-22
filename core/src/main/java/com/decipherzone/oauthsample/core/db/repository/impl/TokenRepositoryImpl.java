package com.decipherzone.oauthsample.core.db.repository.impl;

import com.decipherzone.oauthsample.core.db.domain.Token;
import com.decipherzone.oauthsample.core.db.repository.TokenRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TokenRepositoryImpl extends BaseRepository<Token> implements TokenRepository {
}
