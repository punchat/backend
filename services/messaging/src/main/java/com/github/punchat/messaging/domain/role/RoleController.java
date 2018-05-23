package com.github.punchat.messaging.domain.role;

import com.github.punchat.dto.messaging.role.RoleDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO membership check
@RestController
public class RoleController {
    private final RoleService service;
    private final RoleMapper mapper;

    public RoleController(RoleService service, RoleMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @ApiOperation("create role")
    @PostMapping("channels/{channelId}/roles")
    public RoleDto create(@RequestBody RoleDto roleDto) {
        Role role = mapper.roleDtoToRole(roleDto);
        return mapper.roleToRoleDto(service.createRole(role));
    }

    @ApiOperation("get role by name")
    @GetMapping("channels/{channelId}/roles/{role}")
    public RoleDto get(@PathVariable("role") String roleName) {
        return mapper.roleToRoleDto(service.getRole(roleName));
    }

    @ApiOperation("edit role by name")
    @PutMapping("channels/{channelId}/roles/{role}")
    public RoleDto edit(@PathVariable("role") String roleName, @RequestBody RoleDto newRole) {
        Role role = mapper.roleDtoToRole(newRole);
        return mapper.roleToRoleDto(service.editRole(roleName, role.getName(), role.getPermissions()));
    }

    @ApiOperation("add permission from role")
    @PutMapping("channels/{channelId}/roles/{role}/permissions")
    public RoleDto addPermission(@PathVariable("role") String roleName, @RequestBody Permission[] permission) {
        return mapper.roleToRoleDto(service.addPermissions(roleName, permission));
    }

    @ApiOperation("remove permission from role")
    @DeleteMapping("channels/{channelId}/roles/{role}/permissions")
    public RoleDto excludePermission(@PathVariable("role") String roleName, @RequestBody Permission[] permission) {
        return mapper.roleToRoleDto(service.excludePermissions(roleName, permission));
    }

    @ApiOperation("get all available roles")
    @GetMapping("channels/{channelId}/roles")
    public List<RoleDto> get() {
        return mapper.rolesToRoleDtos(service.getAllRoles());
    }
}
