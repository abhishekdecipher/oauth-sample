package com.decipherzone.oauthsample.web.core.rest.filter;

import com.decipherzone.oauthsample.core.Constants;
import com.decipherzone.oauthsample.core.db.domain.Token;
import com.decipherzone.oauthsample.core.exception.AccessDeniedException;
import com.decipherzone.oauthsample.core.security.TokenProvider;
import com.decipherzone.oauthsample.web.core.rest.ResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.decipherzone.oauthsample.core.Constants.ACCESS_TOKEN_HEADER;

//TODO: Make it more abstracted
public class TokenAuthorizationFilter extends BasicAuthenticationFilter {

  private static final Logger LOGGER = LoggerFactory.getLogger(TokenAuthorizationFilter.class);

  private OrRequestMatcher requestMatcher;
  private TokenProvider tokenProvider;

  public TokenAuthorizationFilter(AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
    super(authenticationManager);
    this.tokenProvider = tokenProvider;

    final List<RequestMatcher> requestMatchers = new ArrayList<>();

    for (String url : Constants.API_PUBLIC_BASE_URL_PATTERN) {
      requestMatchers.add(new AntPathRequestMatcher(url));
    }

    requestMatcher = new OrRequestMatcher(requestMatchers);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
    throws IOException, ServletException {
    UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    chain.doFilter(req, res);
  }

  private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {

    if (requestMatcher.matches(request)) {
      return null;
    }

    String token = request.getHeader(ACCESS_TOKEN_HEADER);

    if (StringUtils.isEmpty(token)) {
      LOGGER.warn("Secured headers are not available. Failing authentication check");
      throw new AccessDeniedException("Your session has expired, please log in again to continue", ResponseCode.SESSION_EXPIRED);
    }

    try {
      if (tokenProvider.verify(token)) {
        Token authToken = tokenProvider.retrieve(token);
        return new UsernamePasswordAuthenticationToken(authToken.getUser().getEmail(), null, null);
      }
    } catch (Exception e) {
      LOGGER.info("Failed to decrypt token, failing authentication : {}", e.getMessage());
    }

    throw new AccessDeniedException("Couldn't authenticate user", ResponseCode.ACCESS_DENIED);
  }

}