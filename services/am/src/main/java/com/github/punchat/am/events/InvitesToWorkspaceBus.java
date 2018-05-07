package com.github.punchat.am.events;

import com.github.punchat.events.InviteToWorkspaceEvent;

public interface InvitesToWorkspaceBus {
    void publishInviteToWorkspace(InviteToWorkspaceEvent event);
}
