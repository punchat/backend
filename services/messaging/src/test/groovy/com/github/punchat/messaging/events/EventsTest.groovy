package com.github.punchat.messaging.events

import com.github.punchat.events.AccountCreatedEvent
import com.github.punchat.messaging.ComponentTestsConfiguration
import com.github.punchat.messaging.domain.channel.BroadcastChannelRepository
import com.github.punchat.messaging.domain.channel.DefaultChannels
import com.github.punchat.messaging.domain.channel.DirectChannelRepository
import com.github.punchat.messaging.domain.member.MemberRepository
import com.github.punchat.messaging.domain.role.RoleRepository
import com.github.punchat.messaging.domain.user.User
import com.github.punchat.messaging.domain.user.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.messaging.support.MessageBuilder
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(classes = ComponentTestsConfiguration)
class EventsTest extends Specification {
    private final static Logger LOG = LoggerFactory.getLogger(EventsTest)
    @Autowired
    private Channels channels

    @Autowired
    private UserRepository userRepository

    @Autowired
    private DirectChannelRepository dRepository

    @Autowired
    private BroadcastChannelRepository bRepository

    @Autowired
    private MemberRepository memberRepository

    @Autowired
    private RoleRepository roleRepository

    def "when 'user created event' received then user data will be stored"() {
        when:
        def payload = MessageBuilder.withPayload(new AccountCreatedEvent(2L, null)).build()
        channels.accountCreatedEvents().send(payload)

        then:
        userRepository.getOne(2L) != null
    }


    def "when 'user created event' received then direct channel will be created"() {
        when:
        def payload = MessageBuilder.withPayload(new AccountCreatedEvent(1L, null)).build()
        channels.accountCreatedEvents().send(payload)

        then:
        User user = userRepository.getOne(1L)
        dRepository.findByUser(user) != null
    }

    def "when creating first user then general and random channels will be created"() {
        when:
        def payload = MessageBuilder.withPayload(new AccountCreatedEvent(3L, null)).build()
        channels.accountCreatedEvents().send(payload)

        then:
        bRepository.count() == 2
        bRepository.findByName(DefaultChannels.GENERAL) != null
        bRepository.findByName(DefaultChannels.RANDOM) != null
    }

    def "general and random channels will be created once"() {
        when:
        def payload1 = MessageBuilder.withPayload(new AccountCreatedEvent(3L, null)).build()
        def payload2 = MessageBuilder.withPayload(new AccountCreatedEvent(5L, null)).build()
        def payload3 = MessageBuilder.withPayload(new AccountCreatedEvent(7L, null)).build()
        channels.accountCreatedEvents().send(payload1)
        channels.accountCreatedEvents().send(payload2)
        channels.accountCreatedEvents().send(payload3)

        then:
        bRepository.count() == 2
        bRepository.findByName(DefaultChannels.GENERAL) != null
        bRepository.findByName(DefaultChannels.RANDOM) != null
    }
}
