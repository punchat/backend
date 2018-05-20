package com.github.punchat.messaging.domain.role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);

    boolean existsByName(String name);
    Role findByName(String name);
}
