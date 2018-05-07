package com.github.punchat.am.events;

import com.github.punchat.events.InviteToChannelEvent;
import com.github.punchat.events.InviteToWorkspaceEvent;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

public class InvitesToWorkspaceBusImpl implements InvitesToWorkspaceBus {
    private final MessageChannel inviteToWorkspaceEvent;

    public InvitesToWorkspaceBusImpl(MessageChannel inviteToWorkspaceEvent) {
        this.inviteToWorkspaceEvent = inviteToWorkspaceEvent;
    }

    @Override
    public void publishInviteToWorkspace(InviteToWorkspaceEvent event) {
        inviteToWorkspaceEvent.send(MessageBuilder.withPayload(event).build());
    }
}
