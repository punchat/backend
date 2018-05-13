package com.github.punchat.notifications.events;

import com.github.punchat.events.InviteToWorkspaceEvent;
import com.github.punchat.log.Trace;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Trace
@Component
@EnableBinding(Channels.class)
public class NewInviteToWorkspaceEventProcessor {
    private final MessageChannel channel;
    private final NewEmailEventBuilder<InviteToWorkspaceEvent> builder;

    public NewInviteToWorkspaceEventProcessor(Channels channels, NewEmailEventBuilder<InviteToWorkspaceEvent> builder) {
        channel = channels.newEmailsEvents();
        this.builder = builder;
    }

    @StreamListener(Channels.INVITE_TO_WORKSPACE_EVENTS)
    public void newInviteToWorkspaceEvent(InviteToWorkspaceEvent event) {
        channel.send(MessageBuilder.withPayload(builder.create(event)).build());
    }
}
