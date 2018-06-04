package com.github.punchat.stomp.events

import org.springframework.cloud.stream.annotation.Output
import org.springframework.messaging.SubscribableChannel

interface Channels {
    String NEW_BROADCAST_MESSAGE_EVENTS = "newBroadcastMessageEvents"
    String NEW_DIRECT_MESSAGE_EVENTS = "newDirectMessageEvents"

    @Output(Channels.NEW_BROADCAST_MESSAGE_EVENTS)
    SubscribableChannel newBroadcastMessageEvents()

    @Output(Channels.NEW_DIRECT_MESSAGE_EVENTS)
    SubscribableChannel newDirectMessageEvents()
}