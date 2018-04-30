package com.github.punchat.uaa.security.properties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties({MicroservicesRegistrationProperties.class})
public class PropertiesConfig {
    @Bean
    @ConfigurationProperties("security.oauth2.client")
    public Client client() {
        log.info("creating client props");
        return new Client();
    }
}


