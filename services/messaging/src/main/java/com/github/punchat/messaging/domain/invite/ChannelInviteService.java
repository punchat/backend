package com.github.punchat.messaging.domain.invite;

import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.role.Role;
import com.github.punchat.messaging.domain.user.User;

import java.util.Set;

public interface ChannelInviteService {
    Set<ChannelInvite> getAuthorizedUserInvites();

    ChannelInvite createChannelInvite(BroadcastChannel channel, User recipient, Role role);

    ChannelInvite acceptInvite(ChannelInvite invite);
}
