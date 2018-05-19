package com.github.punchat.messaging.domain.role;

import com.github.punchat.messaging.id.IdService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DefaultRoleAutoCreator implements CommandLineRunner {
    private final RoleRepository repository;
    private final IdService idService;

    public DefaultRoleAutoCreator(RoleRepository repository, IdService idService) {
        this.repository = repository;
        this.idService = idService;
    }

    @Override
    public void run(String... args) throws Exception {
        Role role = new Role();
        role.setId(idService.next());
        role.setName(DefaultRoles.OWNER);
        role.setPermissions(Arrays.asList(Permission.values()));
        repository.save(role);
    }
}
