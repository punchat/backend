package com.github.punchat.messaging.domain.role

import com.github.punchat.messaging.MockIdService
import com.github.punchat.messaging.id.IdService
import org.junit.Ignore
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

    void setup() {
        service = new RoleServiceImpl(repository, idService)
    }

    def "name can be changed"() {
        given:
        def role = new Role()
        role.name = "example"
        role.id = 0
        role.permissions = []
        repository.save(role)

        when:
        service.editRole("example", "newexample", [])

        then:
        repository.findByName("newexample") != null
    }

    def "name can be kept"() {
        given:
        def role = new Role()
        role.name = "example"
        role.id = 0
        role.permissions = []
        repository.save(role)

        when:
        service.editRole("example", "example", [])

        then:
        repository.findByName("example") != null
    }

    def "to edit role member should have special permission"() {

    }
}
