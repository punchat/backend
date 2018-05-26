package com.github.punchat.messaging.events;

import com.github.punchat.events.InviteToChannelEvent;
import com.github.punchat.events.NewMemberInChannelEvent;
import com.github.punchat.log.Trace;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Trace
@Component
@EnableBinding(Channels.class)
public class EventBusImpl implements EventBus {
    private final MessageChannel inviteToChannelEvent;
    private final MessageChannel newMemberInChannelEvent;

    public EventBusImpl(Channels channels) {
        this.inviteToChannelEvent = channels.inviteToChannelEvents();
        this.newMemberInChannelEvent = channels.newMemberInChannel();
    }

    @Override
    public void publish(InviteToChannelEvent event) {
        inviteToChannelEvent.send(MessageBuilder.withPayload(event).build());
    }

    @Override
    public void publish(NewMemberInChannelEvent event) {
        newMemberInChannelEvent.send(MessageBuilder.withPayload(event).build());
    }
}
