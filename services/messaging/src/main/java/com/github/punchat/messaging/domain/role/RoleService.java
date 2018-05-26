package com.github.punchat.messaging.domain.role;

import com.github.punchat.dto.messaging.role.RoleRequest;
import com.github.punchat.messaging.domain.channel.BroadcastChannel;

import java.util.Set;

public interface RoleService {
    Role create(RoleRequest request);

    Role edit(Role role, RoleRequest request);

    Set<Role> createDefaultRoles(BroadcastChannel channel);
}
