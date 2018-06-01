package com.github.punchat.messaging.domain.channel

import com.github.punchat.dto.messaging.channel.BroadcastChannelRequest
import com.github.punchat.messaging.ComponentTestsConfiguration
import com.github.punchat.messaging.MockIdService
import com.github.punchat.messaging.domain.member.MemberFinderImpl
import com.github.punchat.messaging.domain.member.MemberRepository
import com.github.punchat.messaging.domain.member.MemberService
import com.github.punchat.messaging.domain.role.*
import com.github.punchat.messaging.id.IdService
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

@DataJpaTest
@ActiveProfiles("test")
@ContextConfiguration(classes = ComponentTestsConfiguration)
class ChannelServiceImplTest extends Specification {
    @Autowired
    BroadcastChannelRepository bcRepo

    @Autowired
    RoleRepository roleRepo

    @Autowired
    MemberRepository memberRepo

    ChannelServiceImpl service

    @Autowired
    BroadcastChannelRepository realRepo

    BroadcastChannelRepository mockBcRepo

    void setup() {
        mockBcRepo = Mock(BroadcastChannelRepository)
        IdService idService = new MockIdService()
        BroadcastChannelFinder bcFinder = new BroadcastChannelFinderImpl(bcRepo)
        RoleFinder rFinder = new RoleFinderImpl(roleRepo)
        service = new ChannelServiceImpl(
                null,
                idService,
                mockBcRepo,
                Mappers.getMapper(ChannelMapper),
                Mock(MemberService),
                new RoleServiceImpl(idService, Mappers.getMapper(RoleMapper), roleRepo, bcFinder),
                new MemberFinderImpl(memberRepo),
                bcFinder,
                rFinder
        )
    }

    @Transactional
    def 'channel should have default role'() {
        given:
        mockBcRepo.save(_ as BroadcastChannel) >> { BroadcastChannel channel ->
            return realRepo.save(channel)
        } >> { BroadcastChannel channel ->
            assert channel.defaultRole != null
            return realRepo.save(channel)
        }
        when:
        service.createFor(null, new BroadcastChannelRequest())

        then:
        noExceptionThrown()
    }
}
