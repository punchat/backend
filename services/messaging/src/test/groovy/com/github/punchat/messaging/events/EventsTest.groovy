package com.github.punchat.messaging.events

import com.github.punchat.events.AccountCreatedEvent
import com.github.punchat.messaging.ComponentTestsConfiguration
import com.github.punchat.messaging.domain.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.messaging.support.MessageBuilder
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest(classes = ComponentTestsConfiguration)
@ActiveProfiles("test")
class EventsTest extends Specification {
    @Autowired
    private Channels channels

    @Autowired
    private UserRepository userRepository

    def "when 'user created event' received then user data will be stored"() {
        when:
        def payload = MessageBuilder.withPayload(new AccountCreatedEvent(2L, null)).build()
        channels.accountCreatedEvents().send(payload)

        then:
        userRepository.getOne(2L) != null
    }
}
