package com.github.punchat.uaa.events;

import com.github.punchat.events.AccountCreatedEvent;

public interface EventBus {
    void publish(AccountCreatedEvent event);
}
