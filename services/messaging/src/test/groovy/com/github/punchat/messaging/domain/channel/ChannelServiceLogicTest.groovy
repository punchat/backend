package com.github.punchat.messaging.domain.channel

import com.github.punchat.dto.messaging.channel.BroadcastChannelRequest
import com.github.punchat.messaging.ComponentTestsConfiguration
import com.github.punchat.messaging.domain.role.DefaultRoles
import com.github.punchat.messaging.domain.user.User
import com.github.punchat.messaging.domain.user.UserService
import com.github.punchat.messaging.security.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles(["test", "unsecured"])
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(classes = ComponentTestsConfiguration)
class ChannelServiceLogicTest extends Specification {
    @Autowired
    ChannelServiceImpl service

    @Autowired
    UserService userService

    void setup() {
        User user = userService.createUser(1L)
        AuthService authService = Mock(AuthService)
        authService.getAuthorizedUser() >> user
        service.authService = authService
    }

    def "channel will be created with default role"() {
        given:
        BroadcastChannelRequest request = new BroadcastChannelRequest()
        request.name = "test"
        request.privacy = com.github.punchat.dto.messaging.channel.Privacy.PUBLIC

        when:
        BroadcastChannel channel = service.create(request)

        then:
        channel.defaultRole != null
        channel.defaultRole.name == DefaultRoles.DEFAULT
    }
}
