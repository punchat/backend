package com.github.punchat.messaging.domain.role;

import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByName(String name);

    Optional<Role> findByName(String name);

    Set<Role> findByChannel(BroadcastChannel channel);

    Role findByChannelAndName(BroadcastChannel channel, String name);
}
