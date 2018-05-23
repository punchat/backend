package com.github.punchat.messaging.domain.role;

import com.github.punchat.messaging.domain.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RoleFinderImpl implements RoleFinder {
    private final RoleRepository repository;

    public RoleFinderImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role byId(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("role", id));
    }

    @Override
    public Role byName(String name) {
        return repository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("role", name));
    }

    @Override
    public Role owner() {
        return byName(DefaultRoles.OWNER);
    }
}
