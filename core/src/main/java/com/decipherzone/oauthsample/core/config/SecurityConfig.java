package com.decipherzone.oauthsample.core.config;

import com.decipherzone.oauthsample.core.Constants;
import com.decipherzone.oauthsample.core.security.TokenProvider;
import com.decipherzone.oauthsample.core.service.AuthenticationService;
import com.decipherzone.oauthsample.core.service.UserService;
import com.decipherzone.oauthsample.web.core.rest.filter.TokenAuthenticationFilter;
import com.decipherzone.oauthsample.web.core.rest.filter.TokenAuthorizationFilter;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private TokenProvider tokenProvider;
  private UserService userService;
  private AuthenticationService authenticationService;

  @Autowired
  public SecurityConfig(@Lazy TokenProvider tokenProvider, @Lazy UserService userService, @Lazy AuthenticationService authenticationService) {
    this.tokenProvider = tokenProvider;
    this.userService = userService;
    this.authenticationService = authenticationService;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
      .disable()
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .cors()
      .and()
      .requestCache()
      .requestCache(new NullRequestCache())
      .and()
      .authorizeRequests()
      .antMatchers(HttpMethod.OPTIONS, Constants.OPTIONS_ALLOWED_URL_PATTERN)
      .permitAll()
      .antMatchers(Constants.API_PUBLIC_BASE_URL_PATTERN)
      .permitAll()
      .antMatchers("/**")
      .permitAll()
      .and()
      .authorizeRequests()
      .anyRequest()
      .authenticated()
      .and()
      .exceptionHandling()
      .authenticationEntryPoint(unauthorizedEntryPoint())
      .and()
      .addFilterBefore(new TokenAuthenticationFilter(authenticationManager(), tokenProvider, userService),
        UsernamePasswordAuthenticationFilter.class)
      .addFilter(new TokenAuthorizationFilter(authenticationManager(), tokenProvider))
      .userDetailsService(authenticationService);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationEntryPoint unauthorizedEntryPoint() {
    return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.addAllowedHeader("*");
    configuration.setAllowedOrigins(Lists.newArrayList("*"));
    configuration.setAllowCredentials(true);
    configuration.setAllowedMethods(Arrays.asList("OPTIONS", "GET", "POST", "PUT", "DELETE", "PATCH"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}