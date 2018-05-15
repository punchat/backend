package com.github.punchat.messaging.domain.role;

import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class RoleController {
    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    //creating custom role
    @PostMapping("/role")
    public Role create(@RequestBody Role role) {
        return service.createRole(role);
    }

    //getting particular role
    @GetMapping("/role/{id}")
    public Role get(@PathVariable Long id) {
        throw new UnsupportedOperationException();
    }

    //getting all existing roles
    @GetMapping("/role")
    public Set<Role> get() {
        throw new UnsupportedOperationException();
    }
}
