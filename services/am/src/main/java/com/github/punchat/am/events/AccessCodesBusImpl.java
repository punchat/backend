package com.github.punchat.am.events;

import com.github.punchat.events.AccessCodeGeneratedEvent;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

public class AccessCodesBusImpl implements AccessCodesBus {
    private final MessageChannel accessCodeGeneratedEvent;

    public AccessCodesBusImpl(MessageChannel accessCodeGeneratedEvent) {
        this.accessCodeGeneratedEvent = accessCodeGeneratedEvent;
    }

    @Override
    public void publishAccessCodeGenerated(AccessCodeGeneratedEvent event) {
        accessCodeGeneratedEvent.send(MessageBuilder.withPayload(event).build());
    }
}
