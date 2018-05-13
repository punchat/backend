package com.github.punchat.messaging.domain.role;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @PostMapping("/role")
    public Role create(@RequestBody Role role) {
        return service.createRole(role);
    }
}
