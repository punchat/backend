package com.github.punchat.uaa.security;

import com.github.punchat.uaa.account.AccountRepository;
import com.github.punchat.uaa.security.properties.Client;
import com.github.punchat.uaa.security.properties.MicroservicesRegistrationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.util.Arrays;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
    private final AuthenticationManager authenticationManager;
    private final AccountRepository accountRepository;
    private final PasswordEncoder encoder;
    private final MicroservicesRegistrationProperties microservices;
    private final Client client;

    public AuthServerConfig(AuthenticationManager authenticationManager, AccountRepository accountRepository, PasswordEncoder encoder, MicroservicesRegistrationProperties microservices, Client client) {
        this.authenticationManager = authenticationManager;
        this.accountRepository = accountRepository;
        this.encoder = encoder;
        if (microservices == null) {
            log.info("there is no initial clients configuration");
            microservices = new MicroservicesRegistrationProperties();
        }
        this.microservices = microservices;
        this.client = client;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()");
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(
                Arrays.asList(tokenEnhancer(), jwtAccessTokenConverter()));

        endpoints.tokenStore(tokenStore())
                .tokenEnhancer(tokenEnhancerChain)
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST, HttpMethod.OPTIONS);
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer(accountRepository);
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
                new ClassPathResource("jwt.jks"), "mySecretKey".toCharArray()
        );
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jwt"));
        return converter;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        log.info("signing up {}", client.getClientId());
        ClientDetailsServiceBuilder<InMemoryClientDetailsServiceBuilder> builder = clients.inMemory()
                .withClient(client.getClientId())
                .secret(encoder.encode(client.getClientSecret()))
                .scopes("server")
                .autoApprove(true)
                .authorizedGrantTypes("client_credentials")
                .and();
        for (MicroservicesRegistrationProperties.Microservice microservice : microservices.getClients()) {
            log.info("signing up {}", microservice.getClientId());
            builder = builder.withClient(microservice.getClientId())
                    .secret(encoder.encode(microservice.getClientSecret()))
                    .authorizedGrantTypes(microservice.getGrantTypes().stream().toArray(String[]::new))
                    .and();
        }
    }
}
