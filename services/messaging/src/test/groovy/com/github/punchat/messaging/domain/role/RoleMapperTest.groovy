package com.github.punchat.messaging.domain.role

import com.github.punchat.dto.messaging.role.RoleResponse
import com.github.punchat.messaging.domain.channel.BroadcastChannel
import org.mapstruct.factory.Mappers
import spock.lang.Specification


class RoleMapperTest extends Specification {
    RoleMapper mapper = Mappers.getMapper(RoleMapper)

    def 'field should be mapped'() {
        given:
        Role role = new Role(
                id: 1L,
                channel: new BroadcastChannel(),
                name: 'test',
                permissions: [Permission.CAN_WRITE_MESSAGES]
        )

        when:
        RoleResponse response = mapper.toResponse(role)

        then:
        response.permissions.size() == 1
    }
}
