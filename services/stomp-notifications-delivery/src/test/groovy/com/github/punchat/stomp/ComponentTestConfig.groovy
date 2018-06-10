package com.github.punchat.stomp

import com.github.punchat.stomp.messaging.MessagingService
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class ComponentTestConfig {
    @Bean
    MessagingService messagingService() {
        return new MockMessagingService()
    }
}
