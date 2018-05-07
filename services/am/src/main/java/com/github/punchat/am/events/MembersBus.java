package com.github.punchat.am.events;

import com.github.punchat.events.NewMemberInChannelEvent;

public interface MembersBus {
    void publishNewMemberInChannel(NewMemberInChannelEvent event);
}
