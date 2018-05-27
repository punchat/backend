package com.github.punchat.messaging.domain.message

import com.github.punchat.dto.messaging.message.DirectMessageRequest
import com.github.punchat.messaging.ComponentTestsConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles("test")
@SpringBootTest(classes = ComponentTestsConfiguration)
class DirectMessageFacadeServiceImplTest extends Specification {
    @Autowired
    DirectMessageFacadeServiceImpl service

    def "okyt"() {
        when:
        service.create(new DirectMessageRequest())

        then:
        noExceptionThrown()
    }
}
