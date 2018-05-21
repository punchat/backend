package com.github.punchat.messaging;

import com.github.punchat.messaging.id.IdService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MessagingApplicationTestsConfig {
    @Bean
    public IdService idService() {
        return new MockIdService();
    }
}
