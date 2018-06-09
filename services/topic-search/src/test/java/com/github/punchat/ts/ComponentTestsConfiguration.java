package com.github.punchat.ts;

import com.github.punchat.ts.messaging.MessagingService;
import com.github.punchat.ts.sergey.SergeyService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@TestConfiguration
public class ComponentTestsConfiguration {
    @Bean
    @Profile("test")
    public SergeyService mockSergeyService() {
        return new MockSergeyService();
    }

    @Bean
    @Profile("test")
    public MessagingService mockMessagingService() {
        return new MockMessagingService();
    }
}
