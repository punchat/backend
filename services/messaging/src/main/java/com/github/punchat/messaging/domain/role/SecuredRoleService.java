package com.github.punchat.messaging.domain.role;

import com.github.punchat.dto.messaging.role.RoleRequest;
import com.github.punchat.log.Trace;
import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.channel.BroadcastChannelFinder;
import com.github.punchat.messaging.domain.member.Member;
import com.github.punchat.messaging.domain.member.MemberFinder;
import com.github.punchat.messaging.domain.user.User;
import com.github.punchat.messaging.security.AuthService;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Trace
@AllArgsConstructor
public class SecuredRoleService implements RoleService {
    private final RoleService unsecured;
    private final AuthService authService;
    private final MemberFinder memberFinder;
    private final BroadcastChannelFinder bcFinder;
    private final RoleMapper mapper;

    @Override
    public Role create(RoleRequest request) {
        checkPermissions(request.getChannelId(), Collections.singletonList(Permission.CAN_CREATE_ROLES));
        Role role = mapper.toRole(request);
        checkPermissions(request.getChannelId(), role.getPermissions());
        return unsecured.create(request);
    }

    @Override
    public Role edit(Role role, RoleRequest request) {
        Set<Permission> granted = permissions(request);
        checkPermissions(request.getChannelId(), granted);
        if (request.getPermissions() != null && !request.getPermissions().isEmpty()) {
            role.setPermissions(granted);
        }
        return unsecured.edit(role, request);
    }

    @Override
    public Set<Role> createDefaultRoles(BroadcastChannel channel) {
        return unsecured.createDefaultRoles(channel);
    }

    private void checkPermissions(Long channelId, Collection<Permission> granted) {
        User authorized = authService.getAuthorizedUser();
        BroadcastChannel channel = bcFinder.byId(channelId);
        Member initiator = memberFinder.byUserAndChannel(authorized, channel);
        for (Permission permission : granted) {
            if (!initiator.hasPermission(permission)) {
                throw new AbsentPermissionException(initiator.getId(), permission);
            }
        }
    }

    private Set<Permission> permissions(RoleRequest request) {
        return mapper.toRole(request).getPermissions();
    }
}
