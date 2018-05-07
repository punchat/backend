package com.github.punchat.am.events;

import com.github.punchat.events.AccessCodeGeneratedEvent;
import com.github.punchat.events.InviteToChannelEvent;

public interface InvitesToChannelBus {
    void publishInviteToChannel(InviteToChannelEvent event);
}
