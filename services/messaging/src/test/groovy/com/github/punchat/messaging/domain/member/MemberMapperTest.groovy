package com.github.punchat.messaging.domain.member

import com.github.punchat.dto.messaging.member.MemberResponse
import com.github.punchat.messaging.domain.channel.BroadcastChannel
import com.github.punchat.messaging.domain.role.Role
import com.github.punchat.messaging.domain.user.User
import org.mapstruct.factory.Mappers
import spock.lang.Specification


class MemberMapperTest extends Specification {
    MemberMapper mapper = Mappers.getMapper(MemberMapper)

    def 'fields should be mapped'() {
        given:
        Member member = new Member(
                id: 1L,
                user: new User(),
                channel: new BroadcastChannel(),
                role: new Role()
        )

        when:
        MemberResponse response = mapper.toResponse(member)

        then:
        response.role != null
        response.channel != null
        response.user != null
    }
}
