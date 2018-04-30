package com.github.punchat.messaging.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
    String ACCOUNT_CREATED_EVENTS = "accountCreatedEvents";

    @Input(ACCOUNT_CREATED_EVENTS)
    SubscribableChannel accountCreatedEvents();
}
