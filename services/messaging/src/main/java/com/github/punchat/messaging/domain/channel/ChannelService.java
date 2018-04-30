package com.github.punchat.messaging.domain.channel;

public interface ChannelService {
    DirectChannel getDirectChannel(long userId);

    BroadcastChannel createBroadcastChannel(BroadcastChannel channel);

    BroadcastChannel get(Long id);
}
