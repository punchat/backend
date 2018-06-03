package com.github.punchat.messaging.domain.message

import com.github.punchat.dto.messaging.message.DirectMessageRequest
import com.github.punchat.messaging.ComponentTestsConfiguration
import com.github.punchat.messaging.MockIdService
import com.github.punchat.messaging.domain.channel.DirectChannelFinderImpl
import com.github.punchat.messaging.domain.channel.DirectChannelRepository
import com.github.punchat.messaging.domain.user.User
import com.github.punchat.messaging.domain.user.UserFinderImpl
import com.github.punchat.messaging.domain.user.UserRepository
import com.github.punchat.messaging.domain.user.UserService
import com.github.punchat.messaging.security.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

<<<<<<< HEAD
@ActiveProfiles("test")
=======
@ActiveProfiles(["test", "unsecured"])
>>>>>>> dev
@SpringBootTest(classes = ComponentTestsConfiguration)
class DirectMessageServiceImplTest extends Specification {

    @Autowired
    UserService userService
    @Autowired
    DirectMessageRepository directMessageRepository
    @Autowired
    UserRepository userRepository
    @Autowired
    DirectChannelRepository directChannelRepository

    DirectMessageServiceImpl service

    void setup() {
        User user = userService.createUser(32L)
        AuthService authService = Mock(AuthService)
        authService.authorizedUser >> user
        service = new DirectMessageServiceImpl(authService,
                new MockIdService(),
                directMessageRepository,
                new UserFinderImpl(userRepository),
                new DirectChannelFinderImpl(directChannelRepository)
        )
    }

    def "test"() {
        given:
        DirectMessageRequest request = new DirectMessageRequest()
        request.receiverId = 32L
        request.text = "hi there"

        when:
        DirectMessage msg = service.create(request)

        then:
        msg.text == "hi there"
    }
}
