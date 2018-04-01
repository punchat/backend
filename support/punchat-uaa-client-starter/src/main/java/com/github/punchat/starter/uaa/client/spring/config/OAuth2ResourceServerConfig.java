package com.github.punchat.starter.uaa.client.spring.config;


import com.github.alexivchenko.spring.uaa.client.DefaultAuthContext;
import com.github.punchat.starter.uaa.client.auth.UserPayload;
import com.github.punchat.starter.uaa.client.context.AuthContext;
import com.github.punchat.starter.uaa.client.context.AuthContextImpl;
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
