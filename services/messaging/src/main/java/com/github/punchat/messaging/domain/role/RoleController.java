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

    @GetMapping("/role/{name}")
    public RoleDto get(@PathVariable String name) {
        return mapper.roleToRoleDto(service.getRole(name));
    }

    @PutMapping("/role/{name}")
    public RoleDto edit(@PathVariable String name, @RequestBody Role role) {
        return mapper.roleToRoleDto(service.getRole(name));
    }

    @PatchMapping("/role/{name}/add")
    public RoleDto addPermission(@PathVariable String name, @RequestBody Permission permission) {
        return mapper.roleToRoleDto(service.addPermission(name, permission));
    }

    @PatchMapping("/role/{name}/exclude")
    public RoleDto excludePermission(@PathVariable String name, @RequestBody Permission permission) {
        return mapper.roleToRoleDto(service.excludePermission(name, permission));
    }

    @GetMapping("/role")
    public List<RoleDto> get() {
        return mapper.rolesToRoleDtos(service.getAllRoles());
    }
}
