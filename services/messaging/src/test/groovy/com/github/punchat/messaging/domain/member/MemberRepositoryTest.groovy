package com.github.punchat.messaging.domain.member

import com.github.punchat.messaging.domain.channel.BroadcastChannel
import com.github.punchat.messaging.domain.channel.BroadcastChannelRepository
import com.github.punchat.messaging.domain.role.Role
import com.github.punchat.messaging.domain.role.RoleRepository
import com.github.punchat.messaging.domain.user.User
import com.github.punchat.messaging.domain.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@DataJpaTest
@ActiveProfiles("test")
class MemberRepositoryTest extends Specification {
    @Autowired
    MemberRepository repository
    @Autowired
    BroadcastChannelRepository bcChannel
    @Autowired
    RoleRepository roleRepository
    @Autowired
    UserRepository userRepository

    def "find by list of ids test"() {
        given:
        User user1 = new User()
        user1.setId(4654L)
        userRepository.save(user1)
        User user2 = new User()
        user2.setId(4656L)
        userRepository.save(user2)

        BroadcastChannel channel = new BroadcastChannel()
        channel.id = 32L
        bcChannel.save(channel)

        Role role = new Role()
        role.id = 579L
        role.channel = channel
        roleRepository.save(role)

        Member member1 = new Member()
        member1.id = 1L
        member1.channel = channel
        member1.role = role
        member1.user = user1
        Member member2 = new Member()
        member2.id = 2L
        member2.channel = channel
        member2.role = role
        member2.user = user2

        when:
        repository.saveAll([member1, member2])
        Set<Member> members = repository.findByIds([1L, 2L, 3L] as Set<Long>)

        then:
        members.size() == 2
    }
}
