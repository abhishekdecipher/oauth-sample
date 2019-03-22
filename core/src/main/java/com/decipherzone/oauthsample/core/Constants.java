package com.decipherzone.oauthsample.core;


public final class Constants {

  public static final String BASE_PACKAGE = "com.decipherzone.oauthsample";
  public static final String API_BASE_URL = "/api/v1";
  public static final String API_PUBLIC_BASE_URL = "/public/api/v1";
  public static final String[] API_PUBLIC_BASE_URL_PATTERN =
      new String[] {"/public/**", "/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**",
          "/swagger-ui.html", "/webjars/**", "/tw", "/images/**"};

  public static final String OPTIONS_ALLOWED_URL_PATTERN = "/**";
  public static final String API_LOGIN_URL = API_PUBLIC_BASE_URL + "/login";
  public static final String ACCESS_TOKEN_HEADER = "Authorization";

  public static final int UTIL_CORE_POOL_SIZE = 2;
  public static final int UTIL_MAX_POOL_SIZE = 10;
  public static final long UTIL_KEEP_ALIVE_TIME = 5000;

  private Constants() {
  }
}
