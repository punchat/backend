package com.github.punchat.messaging.domain.channel;

import com.github.punchat.messaging.domain.user.User;

import java.util.Set;

public interface ChannelDefaultsService {
    Set<BroadcastChannel> createDefaultChannelsFor(User user);
}
