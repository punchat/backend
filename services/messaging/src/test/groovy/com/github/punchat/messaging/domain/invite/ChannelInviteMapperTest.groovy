package com.github.punchat.messaging.domain.invite

import com.github.punchat.dto.messaging.invite.ChannelInvitationResponse
import com.github.punchat.messaging.domain.channel.BroadcastChannel
import com.github.punchat.messaging.domain.member.Member
import com.github.punchat.messaging.domain.role.Role
import com.github.punchat.messaging.domain.user.User
import org.mapstruct.factory.Mappers
import spock.lang.Specification


class ChannelInviteMapperTest extends Specification {
    ChannelInviteMapper mapper = Mappers.getMapper(ChannelInviteMapper)

    def 'fields should be mapped'() {
        given:
        ChannelInvite invite = new ChannelInvite(
                id: 1L,
                sender: new Member(),
                recipient: new User(),
                role: new Role(),
                state: State.CREATED,
                channel: new BroadcastChannel()
        )

        when:
        ChannelInvitationResponse response = mapper.toResponse(invite)

        then:
        response.state != null
        response.role != null
        response.channel != null
        response.sender != null
        response.recipient != null
    }
}
