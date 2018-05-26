package com.github.punchat.am.events;

import com.github.punchat.events.AccessCodeGeneratedEvent;
import com.github.punchat.events.InviteToWorkspaceEvent;

public interface EventBus {
    void publish(AccessCodeGeneratedEvent event);

    void publish(InviteToWorkspaceEvent event);
}
