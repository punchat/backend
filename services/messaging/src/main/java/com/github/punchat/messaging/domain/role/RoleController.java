package com.github.punchat.messaging.domain.role;

import com.github.punchat.dto.messaging.role.RoleDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {
    private final RoleService service;
    private final RoleMapper mapper;

    public RoleController(RoleService service, RoleMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    //creating custom role
    @PostMapping("/role")
    public RoleDto create(@RequestBody RoleDto roleDto) {
        Role role = mapper.roleDtoToRole(roleDto);
        return mapper.roleToRoleDto(service.createRole(role));
    }

    @GetMapping("/role/{role}")
    public RoleDto get(@PathVariable String role) {

        return mapper.roleToRoleDto(service.getRole(role));
    }

    @PutMapping("/role/{role}")
    public RoleDto edit(@PathVariable String role, @RequestBody Role newRole) {
        return mapper.roleToRoleDto(service.editRole(role, newRole));
    }

    @PatchMapping("/role/{role}/add")
    public RoleDto addPermission(@PathVariable String role, @RequestBody Permission permission) {
        return mapper.roleToRoleDto(service.addPermission(role, permission));
    }

    @PatchMapping("/role/{role}/exclude")
    public RoleDto excludePermission(@PathVariable String role, @RequestBody Permission permission) {
        return mapper.roleToRoleDto(service.excludePermission(role, permission));
    }

    @GetMapping("/role")
    public List<RoleDto> get() {
        return mapper.rolesToRoleDtos(service.getAllRoles());
    }
}
