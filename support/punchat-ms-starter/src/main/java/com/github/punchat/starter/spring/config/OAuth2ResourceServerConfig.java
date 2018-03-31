package com.github.punchat.starter.spring.config;


import com.github.alexivchenko.spring.uaa.client.DefaultAuthContext;
import com.github.punchat.starter.security.auth.UserPayload;
import com.github.punchat.starter.security.context.AuthContext;
import com.github.punchat.starter.security.context.AuthContextImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@Configuration
@EnableFeignClients("com.github.punchat")
public class OAuth2ResourceServerConfig {
    @Bean
    public AuthContext authContext(com.github.alexivchenko.spring.uaa.client.AuthContext<UserPayload> delegate) {
        return new AuthContextImpl(delegate);
    }

    @Bean
    public com.github.alexivchenko.spring.uaa.client.AuthContext<UserPayload> userPayloadAuthContext() {
        return DefaultAuthContext.<UserPayload>builder()
                .type(UserPayload.class)
                .build();
    }
}
