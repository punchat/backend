package com.github.punchat.messaging.domain.message;

import com.github.punchat.messaging.domain.channel.BroadcastChannel;

import java.util.List;

public interface BroadcastMessageFinder {
    BroadcastMessage byId(Long id);

    List<BroadcastMessage> getLast(BroadcastChannel channel, int limit);

    List<BroadcastMessage> getBefore(BroadcastChannel channel, Long id, int limit);

    List<BroadcastMessage> getAfter(BroadcastChannel channel, Long id, int limit);
}
