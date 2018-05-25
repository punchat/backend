package com.github.punchat.messaging.domain.invite;

import com.github.punchat.messaging.domain.channel.BroadcastChannel;

import java.util.Set;

public interface ChannelInviteFinder {
    ChannelInvite byId(Long id);

    Set<ChannelInvite> byChannel(BroadcastChannel channel);
}
