package com.github.punchat.messaging.domain.role;

import com.github.punchat.dto.messaging.role.RoleDto;
import com.github.punchat.dto.messaging.role.RoleRequest;
import com.github.punchat.log.Trace;
import com.github.punchat.messaging.domain.channel.BroadcastChannelFinder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Trace
@Service
@AllArgsConstructor
public class RoleFacadeServiceImpl implements RoleFacadeService {
    private final RoleService service;
    private final RoleFinder finder;
    private final BroadcastChannelFinder bFinder;
    private final RoleMapper mapper;

    @Override
    public RoleDto getById(Long id) {
        return map(finder.byId(id));
    }

    @Override
    public RoleDto create(RoleRequest request) {
        return map(service.create(request));
    }

    @Override
    public Set<RoleDto> getChannelRoles(Long channelId) {
        return map(finder.byChannel(bFinder.byId(channelId)));
    }

    @Override
    public RoleDto edit(Long roleId, RoleRequest request) {
        return map(service.edit(finder.byId(roleId), request));
    }

    private RoleDto map(Role role) {
        return mapper.roleToRoleDto(role);
    }

    private Set<RoleDto> map(Set<Role> roles) {
        return roles.stream()
                .map(this::map)
                .collect(Collectors.toSet());
    }
}
