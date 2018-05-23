package com.github.punchat.messaging.domain.invite;

import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.channel.BroadcastChannelFinder;
import com.github.punchat.messaging.domain.role.Role;
import com.github.punchat.messaging.domain.role.RoleFinder;
import com.github.punchat.messaging.domain.user.User;
import com.github.punchat.messaging.domain.user.UserFinder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class ChannelInviteFacadeServiceImpl implements ChannelInviteFacadeService {
    private final UserFinder userFinder;
    private final BroadcastChannelFinder channelFinder;
    private final RoleFinder roleFinder;
    private final ChannelInviteService service;

    @Override
    public Set<ChannelInvite> getAuthorizedUserInvitations() {
        return service.getAuthorizedUserInvitations();
    }

    @Override
    public ChannelInvite createChannelInvitation(Long channelId, Long recipientId, Long roleId) {
        BroadcastChannel channel = channelFinder.byId(channelId);
        User user = userFinder.byId(recipientId);
        Role role = roleFinder.byId(roleId);
        return service.createChannelInvitation(channel, user, role);
    }

    @Override
    public ChannelInvite acceptChannelInvitation(Long channelId) {
        return service.acceptChannelInvitation(channelFinder.byId(channelId));
    }

    @Override
    public ChannelInvite acceptChannelInvitation(String channelName) {
        return service.acceptChannelInvitation(channelFinder.byName(channelName));
    }
}
