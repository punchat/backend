package com.github.punchat.messaging.domain.role;

import com.github.punchat.dto.messaging.role.RoleDto;
import com.github.punchat.dto.messaging.role.RoleRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel="spring")
public interface RoleMapper {

    Role roleDtoToRole(RoleDto roleDto);

    Role toRole(RoleRequest request);

    RoleDto roleToRoleDto(Role role);

    List<RoleDto> rolesToRoleDtos(List<Role> roles);

    void updateRoleFromRequest(RoleRequest request, @MappingTarget Role role);
}

