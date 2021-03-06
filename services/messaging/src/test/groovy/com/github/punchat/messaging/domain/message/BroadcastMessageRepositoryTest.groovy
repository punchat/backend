package com.github.punchat.messaging.domain.message

import com.github.punchat.messaging.MockIdService
import com.github.punchat.messaging.domain.channel.BroadcastChannel
import com.github.punchat.messaging.domain.channel.BroadcastChannelRepository
import com.github.punchat.messaging.domain.channel.Privacy
import com.github.punchat.messaging.id.IdService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

import java.time.Clock
import java.time.LocalDateTime

@Slf4j
@DataJpaTest
@ActiveProfiles("test")
class BroadcastMessageRepositoryTest extends Specification {
    @Autowired
    BroadcastMessageRepository repository

    @Autowired
    BroadcastChannelRepository bcRepo

    IdService ids = new MockIdService()

    def "LocalDateTime persists correctly"() {
        given:
        BroadcastMessage msg = new BroadcastMessage()
        LocalDateTime time = LocalDateTime.now(Clock.systemUTC())
        msg.id = 1L
        msg.senderMember = null
        msg.createdOn = time
        msg.channel = null
        msg.addressees = []
        repository.save(msg)

        when:
        BroadcastMessage loaded = repository.getOne(1L)

        then:
        loaded.createdOn == time
    }

    def "get last messages in channel test"() {
        given:
        BroadcastChannel channel = new BroadcastChannel()
        channel.id = ids.next()
        channel.name = "test"
        channel.privacy = Privacy.PUBLIC
        bcRepo.save(channel)

        5.times {
            BroadcastMessage msg = new BroadcastMessage()
            LocalDateTime time = LocalDateTime.now(Clock.systemUTC())
            msg.id = ids.next()
            msg.senderMember = null
            msg.text = "message #$it"
            msg.createdOn = time
            msg.channel = channel
            msg.addressees = []
            repository.save msg
            Thread.sleep(5) // to force order
        }

        when:
        Page<BroadcastMessage> last = repository.findLast(channel, new PageRequest(0, 2))

        log.info("last[0] " + last.content[0])
        log.info("last[1] " + last.content[1])

        then:
        last.size() == 2
        last.content[0].text == "message #4"
        last.content[1].text == "message #3"
    }

    def "get messages before specified id test"() {
        given:
        BroadcastChannel channel = new BroadcastChannel()
        channel.id = ids.next()
        channel.name = "test"
        channel.privacy = Privacy.PUBLIC
        bcRepo.save(channel)

        5.times {
            BroadcastMessage msg = new BroadcastMessage()
            LocalDateTime time = LocalDateTime.now(Clock.systemUTC())
            msg.id = it.longValue()
            msg.senderMember = null
            msg.text = "message #$it"
            msg.createdOn = time
            msg.channel = channel
            msg.addressees = []
            repository.save msg
            Thread.sleep(5) // to force order
        }

        when:
        Page<BroadcastMessage> before4 = repository.findBefore(channel, repository.getOne(4L).createdOn, new PageRequest(0, 2))
        Page<BroadcastMessage> before3 = repository.findBefore(channel, repository.getOne(3L).createdOn, new PageRequest(0, 2))

        log.info("before4[0]" + before4.content[0])
        log.info("before4[1]" + before4.content[1])

        log.info("before3[0]" + before3.content[0])
        log.info("before3[1]" + before3.content[1])

        then:
        before4.content.size() == 2
        before4.content[0].text == "message #3"
        before4.content[1].text == "message #2"

        before3.content.size() == 2
        before3.content[0].text == "message #2"
        before3.content[1].text == "message #1"
    }

    def "get messages after specified id test"() {
        given:
        BroadcastChannel channel = new BroadcastChannel()
        channel.id = ids.next()
        channel.name = "test"
        channel.privacy = Privacy.PUBLIC
        bcRepo.save(channel)

        5.times {
            BroadcastMessage msg = new BroadcastMessage()
            LocalDateTime time = LocalDateTime.now(Clock.systemUTC())
            msg.id = it.longValue()
            msg.senderMember = null
            msg.text = "message #$it"
            msg.createdOn = time
            msg.channel = channel
            msg.addressees = []
            repository.save msg
            Thread.sleep(5) // to force order
        }

        when:
        Page<BroadcastMessage> after2 = repository.findAfter(channel, repository.getOne(2L).createdOn, new PageRequest(0, 2))
        Page<BroadcastMessage> after3 = repository.findAfter(channel, repository.getOne(3L).createdOn, new PageRequest(0, 2))

        log.info("after2[0]" + after2.content[0])
        log.info("after2[1]" + after2.content[1])

        log.info("after3[0]" + after3.content[0])

        then:
        after2.content.size() == 2
        after2.content[0].text == "message #4"
        after2.content[1].text == "message #3"

        after3.content.size() == 1
        after3.content[0].text == "message #4"
    }
}
