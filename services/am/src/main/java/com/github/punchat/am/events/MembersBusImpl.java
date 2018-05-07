package com.github.punchat.am.events;

import com.github.punchat.events.NewMemberInChannelEvent;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

public class MembersBusImpl implements MembersBus {
    private final MessageChannel newMemberInChannelEvent;

    public MembersBusImpl(MessageChannel newMemberInChannelEvent) {
        this.newMemberInChannelEvent = newMemberInChannelEvent;
    }

    @Override
    public void publishNewMemberInChannel(NewMemberInChannelEvent event) {
        newMemberInChannelEvent.send(MessageBuilder.withPayload(event).build());
    }
}
