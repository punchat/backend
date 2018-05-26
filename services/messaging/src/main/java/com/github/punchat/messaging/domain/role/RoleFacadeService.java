package com.github.punchat.messaging.domain.role;

import com.github.punchat.dto.messaging.role.RoleDto;
import com.github.punchat.dto.messaging.role.RoleRequest;

import java.util.Set;

public interface RoleFacadeService {
    RoleDto getById(Long id);

    RoleDto create(RoleRequest request);

    Set<RoleDto> getChannelRoles(Long channelId);

    RoleDto edit(Long roleId, RoleRequest request);
}
