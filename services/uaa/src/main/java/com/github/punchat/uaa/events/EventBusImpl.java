package com.github.punchat.uaa.events;

import com.github.punchat.events.AccountCreatedEvent;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Channels.class)
public class EventBusImpl implements EventBus {
    private final MessageChannel accountCreatedEvent;

    public EventBusImpl(Channels channels) {
        this.accountCreatedEvent = channels.accountCreatedEvents();
    }

    @Override
    public void publish(AccountCreatedEvent event) {
        accountCreatedEvent.send(MessageBuilder.withPayload(event).build());
    }
}
