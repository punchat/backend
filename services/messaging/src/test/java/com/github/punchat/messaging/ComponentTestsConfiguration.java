package com.github.punchat.messaging;

import com.github.punchat.messaging.id.IdService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@TestConfiguration
public class ComponentTestsConfiguration {
    @Bean
    @Profile("test")
    public IdService mockIdService() {
        return new MockIdService();
    }
}
