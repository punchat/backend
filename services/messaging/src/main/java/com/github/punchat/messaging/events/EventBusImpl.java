package com.github.punchat.messaging.events;

import com.github.punchat.events.InviteToChannelEvent;
import com.github.punchat.events.NewBroadcastMessageEvent;
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
    private final MessageChannel inviteToChannelEvents;
    private final MessageChannel newMemberInChannelEvents;
    private final MessageChannel newBroadcastMessageEvents;

    public EventBusImpl(Channels channels) {
        this.inviteToChannelEvents = channels.inviteToChannelEvents();
        this.newMemberInChannelEvents = channels.newMemberInChannel();
        this.newBroadcastMessageEvents = channels.newBroadcastMessageEvents();
    }

    @Override
    public void publish(InviteToChannelEvent event) {
        inviteToChannelEvents.send(MessageBuilder.withPayload(event).build());
    }

    @Override
    public void publish(NewMemberInChannelEvent event) {
        newMemberInChannelEvents.send(MessageBuilder.withPayload(event).build());
    }

    @Override
    public void publish(NewBroadcastMessageEvent event) {
        newBroadcastMessageEvents.send(MessageBuilder.withPayload(event).build());
    }
}
