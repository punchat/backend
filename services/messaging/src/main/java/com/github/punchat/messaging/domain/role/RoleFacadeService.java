package com.github.punchat.messaging.domain.role;

import com.github.punchat.dto.messaging.role.RoleResponse;
import com.github.punchat.dto.messaging.role.RoleRequest;

import java.util.Set;

public interface RoleFacadeService {
    RoleResponse getById(Long id);

    RoleResponse create(RoleRequest request);

    Set<RoleResponse> getChannelRoles(Long channelId);

    RoleResponse edit(Long roleId, RoleRequest request);
}
