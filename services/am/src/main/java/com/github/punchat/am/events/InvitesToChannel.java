package com.github.punchat.am.events;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface InvitesToChannel {
    String INVITE_TO_CHANNEL_EVENTS = "inviteToChannelEvents";

    @Output(INVITE_TO_CHANNEL_EVENTS)
    MessageChannel inviteToChannelEvents();
}
