package com.github.punchat.stomp

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("test")
class StompNotificationDeliveryApplicationTest extends Specification {
    def 'context loads'() {
        given:
        def a = 1
    }
}
