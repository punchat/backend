package com.github.punchat.uaa.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MicroservicesRegistrationProperties.class)
public class PropertiesConfig {
    @Bean
    @ConfigurationProperties("security.oauth2.client")
    public Client client() {
        return new Client();
    }
}


