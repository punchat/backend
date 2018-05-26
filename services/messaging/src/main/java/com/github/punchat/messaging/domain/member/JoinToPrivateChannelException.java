package com.github.punchat.messaging.domain.member;

import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.role.ForbiddenException;

public class JoinToPrivateChannelException extends ForbiddenException {
    private final static String FORMAT = "it is not possible to join to private channel %s (%s)";

    public JoinToPrivateChannelException(BroadcastChannel channel) {
        super(String.format(FORMAT, channel.getName(), channel.getId()));
    }
}
