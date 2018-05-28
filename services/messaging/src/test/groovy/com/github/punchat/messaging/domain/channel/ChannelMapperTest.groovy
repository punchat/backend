package com.github.punchat.messaging.domain.channel

import com.github.punchat.dto.messaging.message.BroadcastMessageResponse
import com.github.punchat.messaging.domain.member.Member
import com.github.punchat.messaging.domain.message.BroadcastMessage
import com.github.punchat.messaging.domain.message.MessageMapper
import org.mapstruct.factory.Mappers
import spock.lang.Specification


class ChannelMapperTest extends Specification {
    def "sender should be mapped correctly"() {
        given:
        MessageMapper mapper = Mappers.getMapper(MessageMapper)
        BroadcastMessage msg = new BroadcastMessage()
        Member member = new Member()
        member.id = 54L
        msg.senderMember = member

        when:
        BroadcastMessageResponse response = mapper.toResponse(msg)

        then:
        response.sender.id == 54L
    }
}
