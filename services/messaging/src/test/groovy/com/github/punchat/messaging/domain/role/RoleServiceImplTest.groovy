package com.github.punchat.messaging.domain.role

import com.github.punchat.messaging.MockIdService
import com.github.punchat.messaging.domain.channel.*
import com.github.punchat.messaging.id.IdService
import org.junit.Ignore
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@Ignore
@ActiveProfiles("test")
@DataJpaTest
class RoleServiceImplTest extends Specification {
    IdService idService = new MockIdService()
    @Autowired
    RoleRepository repository

    RoleServiceImpl service

    @Autowired
    BroadcastChannelRepository bcRepo

    void setup() {
        BroadcastChannelFinder bcFinder = new BroadcastChannelFinderImpl(bcRepo)
        service = new RoleServiceImpl(idService, Mappers.getMapper(RoleMapper), repository, bcFinder)
    }
}
