package com.github.punchat.messaging.domain.role;

import com.github.punchat.dto.messaging.role.RoleResponse;
import com.github.punchat.dto.messaging.role.RoleRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface RoleMapper {
    Role toRole(RoleRequest request);

    RoleResponse toResponse(Role role);
}

