package com.github.punchat.messaging.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
    String ACCOUNT_CREATED_EVENTS = "accountCreatedEvents";
    String INVITE_TO_CHANNEL_EVENTS = "inviteToChannelEvents";
    String NEW_MEMBER_IN_CHANNEL = "newMemberInChannel";

    @Input(ACCOUNT_CREATED_EVENTS)
    SubscribableChannel accountCreatedEvents();

    @Output(NEW_MEMBER_IN_CHANNEL)
    MessageChannel newMemberInChannel();

    @Output(INVITE_TO_CHANNEL_EVENTS)
    MessageChannel inviteToChannelEvents();
}
