package com.github.punchat.am.domain.invite.channel;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ChannelInviteAlreadyCreated extends RuntimeException {

    public ChannelInviteAlreadyCreated(Long channelId, Long userId) {
        super(String.format("Invite to BroadcastChannelDto with channelId: %d " +
                "for User with userId: %d already created", channelId, userId));
    }
}
