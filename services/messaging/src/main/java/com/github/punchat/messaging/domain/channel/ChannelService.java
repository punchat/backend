package com.github.punchat.messaging.domain.channel;

import java.util.List;

public interface ChannelService {
    DirectChannel getDirectChannel(Long userId);

    BroadcastChannel createBroadcastChannel(BroadcastChannel channel);

    BroadcastChannel getBroadcastChannelByName(String channel);

    List<BroadcastChannel> getAuthorizedUserChannels();

    List<BroadcastChannel> getUserChannels(Long userId);
}
