package com.github.punchat.messaging.domain.role;

import com.github.punchat.dto.messaging.role.RoleDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface RoleMapper {

    Role roleDtoToRole(RoleDto roleDto);

    RoleDto roleToRoleDto(Role role);

    List<RoleDto> rolesToRoleDtos(List<Role> roles);
}

