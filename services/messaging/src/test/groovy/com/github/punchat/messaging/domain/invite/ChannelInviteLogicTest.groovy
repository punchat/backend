package com.github.punchat.messaging.domain.invite

import com.github.punchat.dto.messaging.channel.BroadcastChannelRequest
import com.github.punchat.dto.messaging.channel.Privacy
import com.github.punchat.dto.messaging.role.Permission
import com.github.punchat.dto.messaging.role.RoleRequest
import com.github.punchat.messaging.ComponentTestsConfiguration
import com.github.punchat.messaging.domain.access.PermissionAssertServiceImpl
import com.github.punchat.messaging.domain.channel.BroadcastChannel
import com.github.punchat.messaging.domain.channel.ChannelServiceImpl
import com.github.punchat.messaging.domain.role.Role
import com.github.punchat.messaging.domain.role.RoleFinder
import com.github.punchat.messaging.domain.role.RoleService
import com.github.punchat.messaging.domain.user.User
import com.github.punchat.messaging.domain.user.UserService
import com.github.punchat.messaging.id.IdService
import com.github.punchat.messaging.security.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles(["test", "unsecured"])
@SpringBootTest(classes = ComponentTestsConfiguration)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ChannelInviteLogicTest extends Specification {
    @Autowired
    ChannelInviteServiceImpl service
    @Autowired
    UserService userService
    @Autowired
    ChannelInviteRepository repository
    @Autowired
    ChannelServiceImpl channelService
    @Autowired
    RoleService roleService
    @Autowired
    RoleFinder roleFinder
    @Autowired
    IdService idService
    @Autowired
    PermissionAssertServiceImpl permissions

    User authorized
    BroadcastChannel channel

    void setup() {
        AuthService authService = Mock(AuthService)
        authorized = userService.createUser(1)
        authService.authorizedUser >> authorized
        service.setAuth(authService)
        BroadcastChannelRequest request = new BroadcastChannelRequest()
        request.name = "test"
        request.privacy = Privacy.PUBLIC
        channelService.authService = authService
        channel = channelService.create(request)
        permissions.authService = authService
    }

    def "should create invitations"() {
        given:
        User recipient = userService.createUser(2L)
        RoleRequest request = new RoleRequest()
        request.name = "role"
        request.permissions = [Permission.CAN_WRITE_MESSAGES] as List<Permission>
        Role role = roleFinder.defaultRole(channel)

        when:
        ChannelInvite invite = service.createChannelInvite(channel, recipient, role)

        then:
        invite.state == State.CREATED
        invite.sender.user == authorized
    }
}
