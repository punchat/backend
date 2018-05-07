package com.github.punchat.am.events;

import com.github.punchat.events.AccessCodeGeneratedEvent;
import com.github.punchat.events.InviteToChannelEvent;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

public class InvitesToChannelBusImpl implements InvitesToChannelBus {
    private final MessageChannel inviteToChannelEvent;

    public InvitesToChannelBusImpl(MessageChannel inviteToChannelEvent) {
        this.inviteToChannelEvent = inviteToChannelEvent;
    }

    @Override
    public void publishInviteToChannel(InviteToChannelEvent event) {
        inviteToChannelEvent.send(MessageBuilder.withPayload(event).build());
    }
}
