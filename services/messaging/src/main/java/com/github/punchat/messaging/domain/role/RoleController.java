package com.github.punchat.messaging.domain.role;

import com.github.punchat.dto.messaging.role.RoleDto;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class RoleController {
    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @PostMapping("/role")
    public RoleDto create(@RequestBody RoleDto role) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/role/{id}")
    public RoleDto get(@PathVariable Long id) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/role")
    public Set<RoleDto> get() {
        throw new UnsupportedOperationException();
    }
}
