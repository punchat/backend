package com.github.punchat.am.events;

import com.github.punchat.events.AccessCodeGeneratedEvent;
import com.github.punchat.events.InviteToChannelEvent;
import com.github.punchat.events.InviteToWorkspaceEvent;
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
    private final MessageChannel accessCodeGeneratedEvent;
    private final MessageChannel inviteToWorkspaceEvent;

    public EventBusImpl(Channels channels) {
        this.accessCodeGeneratedEvent = channels.accessCodeGeneratedEvents();
        this.inviteToWorkspaceEvent = channels.inviteToWorkspaceEvents();
    }
    @Override
    public void publish(AccessCodeGeneratedEvent event) {
        accessCodeGeneratedEvent.send(MessageBuilder.withPayload(event).build());
    }

    @Override
    public void publish(InviteToWorkspaceEvent event) {
        inviteToWorkspaceEvent.send(MessageBuilder.withPayload(event).build());
    }
}
