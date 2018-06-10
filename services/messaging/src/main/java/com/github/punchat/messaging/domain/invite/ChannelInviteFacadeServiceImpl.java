package com.github.punchat.messaging.domain.invite;

import com.github.punchat.dto.messaging.invite.ChannelInvitationResponse;
import com.github.punchat.log.Trace;
import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.channel.BroadcastChannelFinder;
import com.github.punchat.messaging.domain.role.Role;
import com.github.punchat.messaging.domain.role.RoleFinder;
import com.github.punchat.messaging.domain.user.User;
import com.github.punchat.messaging.domain.user.UserFinder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Trace
@Service
@AllArgsConstructor
public class ChannelInviteFacadeServiceImpl implements ChannelInviteFacadeService {
    private final UserFinder userFinder;
    private final BroadcastChannelFinder channelFinder;
    private final RoleFinder roleFinder;
    private final ChannelInviteService service;
    private final ChannelInviteFinder finder;
    private final ChannelInviteMapper mapper;

    @Override
    public ChannelInvitationResponse getById(Long id) {
        return map(finder.byId(id));
    }

    @Override
    public List<ChannelInvitationResponse> getAuthorizedUserInvites() {
        return map(service.getAuthorizedUserInvites());
    }

    @Override
    public List<ChannelInvitationResponse> getChannelInvites(Long id) {
        return map(finder.byChannel(channelFinder.byId(id)));
    }

    @Override
    public ChannelInvitationResponse createChannelInvite(Long channelId, Long recipientId, Long roleId) {
        BroadcastChannel channel = channelFinder.byId(channelId);
        User user = userFinder.byId(recipientId);
        Role role;
        if (roleId == null) {
            role = roleFinder.defaultRole(channel);
        } else {
            role = roleFinder.byId(roleId);
        }
        return map(service.createChannelInvite(channel, user, role));
    }

    @Override
    public ChannelInvitationResponse acceptInvitation(Long id) {
        return map(service.acceptInvite(finder.byId(id)));
    }

    private ChannelInvitationResponse map(ChannelInvite invite) {
        return mapper.toResponse(invite);
    }

    private List<ChannelInvitationResponse> map(Set<ChannelInvite> invites) {
        return invites.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
