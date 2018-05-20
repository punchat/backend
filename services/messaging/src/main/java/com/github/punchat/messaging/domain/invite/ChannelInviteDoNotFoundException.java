package com.github.punchat.messaging.domain.invite;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ChannelInviteDoNotFoundException extends RuntimeException {

    public ChannelInviteDoNotFoundException(String channelName, Long recipientId) {
        super(String.format("Invite to Channel %s " +
                "for User with userId: %d don't found", channelName, recipientId));
    }
}
