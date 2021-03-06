package com.github.punchat.messaging.domain.channel;

import com.github.punchat.dto.messaging.channel.BroadcastChannelRequest;
import com.github.punchat.messaging.domain.user.User;

import java.util.Set;

public interface ChannelService {
    BroadcastChannel create(BroadcastChannelRequest payload);

    BroadcastChannel createFor(User user, BroadcastChannelRequest payload);

    BroadcastChannel update(Long id, BroadcastChannelRequest request);

    Set<BroadcastChannel> getAuthorizedUserChannels();

    Set<BroadcastChannel> getUserChannels(User user);

    void delete(Long id);

    Set<BroadcastChannel> getAllPublicChannels();
}
