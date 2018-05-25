package com.github.punchat.messaging.domain.role;

import com.github.punchat.dto.messaging.role.RoleRequest;
import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.channel.BroadcastChannelFinder;
import com.github.punchat.messaging.domain.member.Member;
import com.github.punchat.messaging.domain.member.MemberFinder;
import com.github.punchat.messaging.domain.user.User;
import com.github.punchat.messaging.id.IdService;
import com.github.punchat.messaging.security.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final AuthService auth;
    private final IdService idService;
    private final RoleMapper mapper;
    private final RoleRepository roleRepository;
    private final BroadcastChannelFinder bFinder;
    private final MemberFinder memberFinder;

    @Override
    public Role create(RoleRequest request) {
        User authorized = auth.getAuthorizedUser();
        BroadcastChannel channel = bFinder.byId(request.getChannelId());
        Member member = memberFinder.byUserAndChannel(authorized, channel);
        if (!member.getRole().getPermissions().contains(Permission.CAN_EXCLUDE_USERS)) {
            throw new AbsentPermissionException(authorized.getId(), Permission.CAN_CREATE_ROLES);
        }
        if (!roleRepository.existsByName(request.getName())) {
            Role role = mapper.toRole(request);
            checkPermissions(member, role.getPermissions());
            role.setId(idService.next());
            role.setChannel(channel);
            return roleRepository.save(role);
        } else {
            throw new RoleAlreadyExistsException(request.getName());
        }
    }

    @Override
    public Role edit(Role role, RoleRequest request) {
        User authorized = auth.getAuthorizedUser();
        BroadcastChannel channel = bFinder.byId(request.getChannelId());
        Member member = memberFinder.byUserAndChannel(authorized, channel);
        if (nameChanged(role, request)) {
            checkName(request.getName());
            role.setName(request.getName());
        }
        if (request.getPermissions() != null && !request.getPermissions().isEmpty()) {
            List<Permission> granted = permissions(request);
            checkPermissions(member, granted);
            role.setPermissions(granted);
        }
        return roleRepository.save(role);
    }

    @Override
    public Set<Role> createDefaultRoles(BroadcastChannel channel) {
        Role owner = new Role();
        owner.setId(idService.next());
        owner.setName(DefaultRoles.OWNER);
        owner.setChannel(channel);
        owner.setPermissions(Arrays.asList(Permission.values()));
        roleRepository.save(owner);
        return Collections.singleton(owner);
    }

    private void checkName(String name) {
        if (roleRepository.existsByName(name)) {
            throw new RoleAlreadyExistsException(name);
        }
    }

    private boolean nameChanged(Role role, RoleRequest request) {
        return !role.getName().equals(request.getName());
    }

    private void checkPermissions(Member initiator, Collection<Permission> granted) {
        for (Permission permission : granted) {
            if (!initiator.hasPermission(permission)) {
                throw new AbsentPermissionException(initiator.getId(), permission);
            }
        }
    }

    private List<Permission> permissions(RoleRequest request) {
        return mapper.toRole(request).getPermissions();
    }
}
