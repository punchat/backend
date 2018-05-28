package com.github.punchat.messaging.events;

import com.github.punchat.events.InviteToChannelEvent;
import com.github.punchat.events.NewBroadcastMessageEvent;
import com.github.punchat.events.NewMemberInChannelEvent;

public interface EventBus {
    void publish(InviteToChannelEvent event);

    void publish(NewMemberInChannelEvent event);

    void publish(NewBroadcastMessageEvent event);
}
