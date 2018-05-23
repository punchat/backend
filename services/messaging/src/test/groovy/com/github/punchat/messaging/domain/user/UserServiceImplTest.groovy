package com.github.punchat.messaging.domain.user

import com.github.punchat.messaging.ComponentTestsConfiguration
import com.github.punchat.messaging.domain.channel.DirectChannelRepository
import com.github.punchat.messaging.id.IdService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@DataJpaTest
@ContextConfiguration(classes = ComponentTestsConfiguration)
@ActiveProfiles("test")
class UserServiceImplTest extends Specification {

    @Autowired
    private UserRepository userRepository

    @Autowired
    private DirectChannelRepository directChannelRepository

    @Autowired
    private IdService idService

    private UserServiceImpl service

    void setup() {
        service = new UserServiceImpl(userRepository, directChannelRepository, idService)
    }

    def "direct channel will be created within the user"() {
        when:
        def user = service.createUser(1)
        then:
        directChannelRepository.findByUser(user) != null
    }
}
