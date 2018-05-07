package com.github.punchat.am.events;


import com.github.punchat.events.AccessCodeGeneratedEvent;
import com.github.punchat.events.InviteToChannelEvent;
import com.github.punchat.events.InviteToWorkspaceEvent;

public interface EventBus {
        void publishInviteToChannelCreated(InviteToChannelEvent event);

        void publishInviteToChannelAccepted(InviteToChannelEvent event);

        void publishInviteToWorkspaceCreated(InviteToWorkspaceEvent event);

        void publishAccessCodeGenerated(AccessCodeGeneratedEvent event);
}
