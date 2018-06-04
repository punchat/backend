package com.github.punchat.messaging.domain.role;

import com.github.punchat.dto.messaging.role.RoleRequest;
import com.github.punchat.dto.messaging.role.RoleResponse;

import java.util.List;

public interface RoleFacadeService {
    RoleResponse getById(Long id);

    RoleResponse create(RoleRequest request);

    List<RoleResponse> getChannelRoles(Long channelId);

    RoleResponse edit(Long roleId, RoleRequest request);
}
