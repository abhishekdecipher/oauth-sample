package com.decipherzone.oauthsample.service;

import com.decipherzone.oauthsample.core.Constants;
import com.decipherzone.oauthsample.core.config.properties.ApplicationProperties;
import com.decipherzone.oauthsample.core.config.properties.DbProperties;
import com.decipherzone.oauthsample.core.config.properties.SecurityProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
@ComponentScan(basePackages = {Constants.BASE_PACKAGE})
@EnableConfigurationProperties({ApplicationProperties.class, DbProperties.class, SecurityProperties.class})
public class Application extends SpringBootServletInitializer {

  private static final Logger LOGGER = LogManager.getLogger(Application.class);

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(Application.class);
  }

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    super.onStartup(servletContext);
    servletContext.getSessionCookieConfig().setHttpOnly(true);
    servletContext.getSessionCookieConfig().setSecure(true);
  }
}
