package com.github.punchat.messaging.domain.channel;

public interface BroadcastChannelFinder {
    BroadcastChannel byId(Long id);

    BroadcastChannel byName(String channelName);
}
