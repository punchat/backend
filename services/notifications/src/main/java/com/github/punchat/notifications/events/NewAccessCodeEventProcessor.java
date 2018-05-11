package com.github.punchat.notifications.events;

import com.github.punchat.events.AccessCodeGeneratedEvent;
import com.github.punchat.events.NewEmailCreatedEvent;
import com.github.punchat.log.Trace;
import com.github.punchat.notifications.events.Channels;
import com.github.punchat.notifications.events.NewEmailEventBuilder;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Trace
@Component
@EnableBinding(Channels.class)
public class NewAccessCodeEventProcessor {
    private final NewEmailEventBuilder<AccessCodeGeneratedEvent> builder;
    private final MessageChannel emails;

    public NewAccessCodeEventProcessor(Channels channels, NewEmailEventBuilder<AccessCodeGeneratedEvent> builder) {
        emails = channels.newEmailsEvents();
        this.builder = builder;
    }

    @SuppressWarnings("unchecked")
    @StreamListener(Channels.NEW_ACCESS_CODE_EVENTS)
    public void newAccessCodeEvent(AccessCodeGeneratedEvent event) {
        NewEmailCreatedEvent email = builder.create(event);
        emails.send(MessageBuilder.withPayload(email).build());
    }
}
