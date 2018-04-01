package com.github.punchat.starter.uaa.client.spring.config;

import com.github.punchat.starter.uaa.client.context.AuthContext;
import com.github.punchat.starter.uaa.client.spring.support.args.AuthMethodArgumentResolver;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @author Alex Ivchenko
 */
@Configuration
@AutoConfigureBefore({DelegatingWebMvcConfiguration.class,  WebMvcAutoConfiguration.class})
public class CustomArgumentResolversProvider extends WebMvcConfigurationSupport {
    private final AuthContext auth;

    public CustomArgumentResolversProvider(AuthContext auth) {
        this.auth = auth;
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new AuthMethodArgumentResolver(auth));
        super.addArgumentResolvers(argumentResolvers);
    }
}
