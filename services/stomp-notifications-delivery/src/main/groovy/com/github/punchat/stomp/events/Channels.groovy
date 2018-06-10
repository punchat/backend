package com.github.punchat.stomp.events

import org.springframework.cloud.stream.annotation.Input
import org.springframework.messaging.SubscribableChannel

interface Channels {
    String NEW_BROADCAST_MESSAGE_EVENTS = "newBroadcastMessageEvents"
    String NEW_DIRECT_MESSAGE_EVENTS = "newDirectMessageEvents"

    @Input(Channels.NEW_BROADCAST_MESSAGE_EVENTS)
    SubscribableChannel newBroadcastMessageEvents()

    @Input(Channels.NEW_DIRECT_MESSAGE_EVENTS)
    SubscribableChannel newDirectMessageEvents()
}