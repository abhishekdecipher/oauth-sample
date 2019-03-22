package com.decipherzone.oauthsample.core.config;


import static com.decipherzone.oauthsample.core.Constants.API_LOGIN_URL;

import com.decipherzone.oauthsample.core.Constants;
import com.decipherzone.oauthsample.web.core.rest.request.LoginRequest;
import com.decipherzone.oauthsample.web.core.rest.response.AuthResponse;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Multimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ApiListingBuilder;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiListing;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Operation;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.DocumentationPluginsManager;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;
import springfox.documentation.spring.web.scanners.ApiDescriptionReader;
import springfox.documentation.spring.web.scanners.ApiListingScanner;
import springfox.documentation.spring.web.scanners.ApiListingScanningContext;
import springfox.documentation.spring.web.scanners.ApiModelReader;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport implements OperationBuilderPlugin {

  private static final String API_INFO_TITLE = "OAuth Sample REST API";
  private static final String APP_VERSION = "1.0.0";
  private static final String APP_NAME = "Open Authentication Sample";
  private static final String URL = "http://localhost:8080";
  private static final String EMAIL = "info@oauth.com";

  @Autowired
  private TypeResolver typeResolver;

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.basePackage(Constants.BASE_PACKAGE))
        .paths(PathSelectors.any())
        .build()
        .additionalModels(typeResolver.resolve(LoginRequest.class))
        .additionalModels(typeResolver.resolve(AuthResponse.class))
        .apiInfo(this.apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title(API_INFO_TITLE)
        .description(API_INFO_TITLE)
        .version(APP_VERSION)
        .contact(new Contact(APP_NAME, URL, EMAIL))
        .build();
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {

    registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

    registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  @Override
  public void apply(OperationContext operationContext) {
    List<Parameter> parameters = new ArrayList<>();
    parameters.add(new ParameterBuilder().parameterType("header")
        .name(Constants.ACCESS_TOKEN_HEADER)
        .description("access token")
        .modelRef(new ModelRef("string"))
        .allowMultiple(false)
        .required(false)
        .build());
    operationContext.operationBuilder().parameters(parameters);
  }

  @Override
  public boolean supports(DocumentationType documentationType) {
    return DocumentationType.SWAGGER_2.equals(documentationType);
  }

  @Primary
  @Bean
  public ApiListingScanner addExtraOperations(ApiDescriptionReader apiDescriptionReader, ApiModelReader apiModelReader,
      DocumentationPluginsManager pluginsManager) {
    return new LoginOperation(typeResolver, apiDescriptionReader, apiModelReader, pluginsManager);
  }


  private class LoginOperation extends ApiListingScanner {

    private TypeResolver typeResolver;

    LoginOperation(TypeResolver typeResolver, ApiDescriptionReader apiDescriptionReader, ApiModelReader apiModelReader,
        DocumentationPluginsManager pluginsManager) {
      super(apiDescriptionReader, apiModelReader, pluginsManager);
      this.typeResolver = typeResolver;
    }

    @Override
    public Multimap<String, ApiListing> scan(ApiListingScanningContext context) {
      final Multimap<String, ApiListing> def = super.scan(context);

      final List<ApiDescription> apis = new LinkedList<>();
      final Set<Tag> tags = new HashSet<>();
      tags.add(new Tag("Authentication", "Authentication Controller"));
      final Set<String> tagNames = new HashSet<>();
      tagNames.add("Authentication");

      final Set<String> contentTypes = new HashSet<>();
      contentTypes.add(MediaType.APPLICATION_JSON_VALUE);

      final Set<ResponseMessage> responseMessages = new HashSet<>();
      responseMessages.add(new ResponseMessage(200, "Success", new ModelRef("AuthResponse"), Collections.emptyMap(),
          Collections.emptyList()));


      final List<Operation> operations = new ArrayList<>();
      operations.add(new OperationBuilder(new CachingOperationNameGenerator()).method(HttpMethod.POST)
          .uniqueId("login")
          .parameters(Arrays.asList(new ParameterBuilder().name("body")
              .required(true)
              .description("Login request")
              .parameterType("body")
              .modelRef(new ModelRef("LoginRequest"))
              .type(typeResolver.resolve(LoginRequest.class))
              .build()))
          .summary("Log in")
          .responseModel(new ModelRef("AuthResponse"))
          .responseMessages(responseMessages)
          .tags(tagNames)
          .consumes(contentTypes)
          .produces(contentTypes)
          .build());

      apis.add(new ApiDescription("Authentication", API_LOGIN_URL, "Login", operations, false));
      def.put("authentication",
          new ApiListingBuilder(context.getDocumentationContext().getApiDescriptionOrdering()).apis(apis)
              .description("Authentication API")
              .tags(tags)
              .tagNames(tagNames)
              .build());

      return def;
    }
  }
}
