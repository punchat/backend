package com.github.punchat.am.events;

import com.github.punchat.events.AccessCodeGeneratedEvent;
import com.github.punchat.events.InviteToChannelEvent;
import com.github.punchat.events.InviteToWorkspaceEvent;
import com.github.punchat.events.NewMemberInChannelEvent;

public interface EventBus {
    void publish(AccessCodeGeneratedEvent event);

    void publish(InviteToChannelEvent event);

    void publish(InviteToWorkspaceEvent event);

    void publish(NewMemberInChannelEvent event);
}
