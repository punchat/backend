package com.github.punchat.messaging.domain.channel;

public interface DirectChannelFinder {
    DirectChannel getDirectChannel(Long userId);
}
