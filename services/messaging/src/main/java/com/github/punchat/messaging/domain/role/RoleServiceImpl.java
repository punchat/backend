package com.github.punchat.messaging.domain.role;

import com.github.punchat.dto.messaging.role.RoleRequest;
import com.github.punchat.log.Trace;
import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.channel.BroadcastChannelFinder;
import com.github.punchat.messaging.id.IdService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Trace
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final IdService idService;
    private final RoleMapper mapper;
    private final RoleRepository roleRepository;
    private final BroadcastChannelFinder bcFinder;

    @Override
    public Role create(RoleRequest request) {
        BroadcastChannel channel = bcFinder.byId(request.getChannelId());
        if (!roleRepository.existsByName(request.getName())) {
            Role role = mapper.toRole(request);
            role.setId(idService.next());
            role.setChannel(channel);
            return roleRepository.save(role);
        } else {
            throw new RoleAlreadyExistsException(request.getName());
        }
    }

    @Override
    public Role edit(Role role, RoleRequest request) {
        if (nameChanged(role, request)) {
            checkName(request.getName());
            role.setName(request.getName());
        }
        if (request.getPermissions() != null && !request.getPermissions().isEmpty()) {
            Set<Permission> granted = permissions(request);
            role.setPermissions(granted);
        }
        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public Set<Role> createDefaultRoles(BroadcastChannel channel) {
        Role owner = createOwnerRole(channel);
        Role def = createDefaultUserRole(channel);
        return new HashSet<>(Arrays.asList(owner, def));
    }

    private Role createOwnerRole(BroadcastChannel channel) {
        return createRole(DefaultRoles.OWNER, Arrays.asList(Permission.values()), channel);
    }

    private Role createDefaultUserRole(BroadcastChannel channel) {
        return createRole(DefaultRoles.DEFAULT, Arrays.asList(Permission.CAN_WRITE_MESSAGES), channel);
    }

    private Role createRole(String name, Collection<Permission> permissions, BroadcastChannel channel) {
        Role role = new Role();
        role.setId(idService.next());
        role.setName(name);
        role.setChannel(channel);
        role.setPermissions(new HashSet<>(permissions));
        roleRepository.save(role);
        return role;
    }

    private void checkName(String name) {
        if (roleRepository.existsByName(name)) {
            throw new RoleAlreadyExistsException(name);
        }
    }

    private boolean nameChanged(Role role, RoleRequest request) {
        return !role.getName().equals(request.getName());
    }

    private Set<Permission> permissions(RoleRequest request) {
        return mapper.toRole(request).getPermissions();
    }
}
