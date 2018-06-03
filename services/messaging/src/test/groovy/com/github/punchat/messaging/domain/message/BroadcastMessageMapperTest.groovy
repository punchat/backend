package com.github.punchat.messaging.domain.message

import com.github.punchat.dto.messaging.message.BroadcastMessageResponse
import com.github.punchat.messaging.domain.channel.BroadcastChannel
import com.github.punchat.messaging.domain.member.Member
import org.mapstruct.factory.Mappers
import spock.lang.Specification


class BroadcastMessageMapperTest extends Specification {
    BroadcastMessageMapper mapper = Mappers.getMapper(BroadcastMessageMapper)

    def 'fields should be mapped'() {
        given:
        BroadcastMessage msg = new BroadcastMessage(
                id: 1L,
                channel: new BroadcastChannel(),
                senderMember: new Member(id: 2L),
                addressees: [new Member(id: 3L), new Member(id: 4L)]
        )

        when:
        BroadcastMessageResponse response = mapper.toResponse(msg)

        then:
        response.id == 1L
        response.sender.id == 2L
        response.addressees.size() == 2
    }
}
