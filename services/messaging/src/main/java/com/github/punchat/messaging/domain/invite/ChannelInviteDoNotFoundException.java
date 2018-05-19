package com.github.punchat.messaging.domain.invite;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ChannelInviteDoNotFoundException extends RuntimeException {

    public ChannelInviteDoNotFoundException(Long channelId, Long userId) {
        super(String.format("Invite to BroadcastChannelDto with channelId: %d " +
                "for User with userId: %d don't found", channelId, userId));
    }
}
