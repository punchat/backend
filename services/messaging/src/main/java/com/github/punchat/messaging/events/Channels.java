package com.github.punchat.messaging.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
    String ACCOUNT_CREATED_EVENTS = "accountCreatedEvents";
    String INVITE_TO_CHANNEL_EVENTS = "inviteToChannelEvents";
    String NEW_MEMBER_IN_CHANNEL = "newMemberInChannel";
    String NEW_BROADCAST_MESSAGE_EVENTS = "newBroadcastMessageEvents";
    String NEW_DIRECT_MESSAGE_EVENTS = "newDirectMessageEvents";

    @Input(ACCOUNT_CREATED_EVENTS)
    SubscribableChannel accountCreatedEvents();

    @Output(NEW_MEMBER_IN_CHANNEL)
    MessageChannel newMemberInChannel();

    @Output(INVITE_TO_CHANNEL_EVENTS)
    MessageChannel inviteToChannelEvents();

    @Output(NEW_BROADCAST_MESSAGE_EVENTS)
    MessageChannel newBroadcastMessageEvents();

    @Output(NEW_DIRECT_MESSAGE_EVENTS)
    MessageChannel newDirectMessageEvents();
}
