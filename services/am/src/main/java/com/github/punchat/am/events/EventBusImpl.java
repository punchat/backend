package com.github.punchat.am.events;

import com.github.punchat.events.AccessCodeGeneratedEvent;
import com.github.punchat.events.InviteToChannelEvent;
import com.github.punchat.events.InviteToWorkspaceEvent;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Invites.class)
public class EventBusImpl implements EventBus {
    private final MessageChannel inviteToChannelCreatedEvent;
    private final MessageChannel inviteToChannelAcceptedEvent;
    private final MessageChannel inviteToWorkspaceCreatedEvent;
    private final MessageChannel accessCodeGeneratedEvent;

    public EventBusImpl(MessageChannel inviteToChannelCreatedEvent,
                        MessageChannel inviteToChannelAcceptedEvent,
                        MessageChannel inviteToWorkspaceCreatedEvent,
                        MessageChannel accessCodeGeneratedEvent) {
        this.inviteToChannelCreatedEvent = inviteToChannelCreatedEvent;
        this.inviteToChannelAcceptedEvent = inviteToChannelAcceptedEvent;
        this.inviteToWorkspaceCreatedEvent = inviteToWorkspaceCreatedEvent;
        this.accessCodeGeneratedEvent = accessCodeGeneratedEvent;
    }

    @Override
    public void publishInviteToChannelCreated(InviteToChannelEvent event) {
        inviteToChannelCreatedEvent.send(MessageBuilder.withPayload(event).build());
    }

    @Override
    public void publishInviteToChannelAccepted(InviteToChannelEvent event) {
        inviteToChannelAcceptedEvent.send(MessageBuilder.withPayload(event).build());
    }

    @Override
    public void publishInviteToWorkspaceCreated(InviteToWorkspaceEvent event) {
        inviteToWorkspaceCreatedEvent.send(MessageBuilder.withPayload(event).build());
    }

    @Override
    public void publishAccessCodeGenerated(AccessCodeGeneratedEvent event) {
        accessCodeGeneratedEvent.send(MessageBuilder.withPayload(event).build());
    }
}
