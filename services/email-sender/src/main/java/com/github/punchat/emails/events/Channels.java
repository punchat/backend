package com.github.punchat.emails.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
    String NEW_EMAILS_EVENTS = "newEmailsEvents";

    @Input(NEW_EMAILS_EVENTS)
    SubscribableChannel newEmailsEvents();
}
