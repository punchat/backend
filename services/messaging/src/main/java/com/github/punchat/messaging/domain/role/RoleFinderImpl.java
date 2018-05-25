package com.github.punchat.messaging.domain.role;

import com.github.punchat.messaging.domain.ResourceNotFoundException;
import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import org.springframework.stereotype.Service;

import java.util.Set;

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
    public Role owner(BroadcastChannel channel) {
        return repository.findByChannelAndName(channel, DefaultRoles.OWNER).orElseThrow(() ->
                new RuntimeException("owner role not found"));
    }

    @Override
    public Set<Role> byChannel(BroadcastChannel channel) {
        return repository.findByChannel(channel);
    }

    @Override
    public Role defaultRole(BroadcastChannel channel) {
        return repository.findByChannelAndName(channel, DefaultRoles.DEFAULT).orElseThrow(() ->
                new RuntimeException("default role not found"));
    }
}
