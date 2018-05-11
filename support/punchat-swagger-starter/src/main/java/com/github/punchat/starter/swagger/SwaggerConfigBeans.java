package com.github.punchat.starter.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;

import java.util.Arrays;
import java.util.Collections;

@Slf4j
@Configuration
@ConditionalOnProperty(value = "swagger.enabled")
public class SwaggerConfigBeans {
    private final SwaggerProperties props;

    public SwaggerConfigBeans(SwaggerProperties props) {
        this.props = props;
        log.info("swagger config enabled");
        log.info("swagger props: {}", props);
    }

    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
                .clientId("client")
                .clientSecret("secret")
                .build();
    }

    @Bean
    public SecurityScheme securityScheme() {
        log.info("token uri: {}", props.getSecurity().getAccessTokenUri());
        ResourceOwnerPasswordCredentialsGrant grant = new ResourceOwnerPasswordCredentialsGrant(props.getSecurity().getAccessTokenUri());
        return new OAuthBuilder().name("Authorization")
                .grantTypes(Collections.singletonList(grant))
                .scopes(Arrays.asList(scopes()))
                .build();
    }

    @Bean
    public SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference("Authorization", scopes())))
                .forPaths(PathSelectors.any())
                .build();
    }

    @Bean
    public AuthorizationScope[] scopes() {
        return new AuthorizationScope[] {
                new AuthorizationScope("openid", "openid scope"),
                new AuthorizationScope("server", "server scope"),
        };
    }
}

