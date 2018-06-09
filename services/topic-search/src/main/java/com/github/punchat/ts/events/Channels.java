package com.github.punchat.ts.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

    String NEW_BROADCAST_MESSAGE_EVENTS = "newBroadcastMessageEvents";
    String NEW_DIRECT_MESSAGE_EVENTS = "newDirectMessageEvents";

    @Input(NEW_BROADCAST_MESSAGE_EVENTS)
    SubscribableChannel newBroadcastMessageEvents();

    @Input(NEW_DIRECT_MESSAGE_EVENTS)
    SubscribableChannel newDirectMessageEvents();
}
