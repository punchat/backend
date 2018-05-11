package com.github.punchat.notifications.events;

import com.github.punchat.events.NewEmailCreatedEvent;

public interface NewEmailEventBuilder<T> {
    NewEmailCreatedEvent create(T event);
}
