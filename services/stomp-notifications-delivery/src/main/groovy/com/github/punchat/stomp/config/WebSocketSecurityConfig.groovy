package com.github.punchat.stomp.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer

@Configuration
class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {
    @Override
    void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages.anyMessage().permitAll()
    }

    @Override
    boolean sameOriginDisabled() {
        return true
    }
}
