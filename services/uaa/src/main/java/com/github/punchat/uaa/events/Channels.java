package com.github.punchat.uaa.events;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Channels {
    String ACCOUNT_CREATED_EVENTS = "accountCreatedEvents";

    @Output(ACCOUNT_CREATED_EVENTS)
    MessageChannel accountCreatedEvents();
}
