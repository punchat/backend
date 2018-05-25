package com.github.punchat.messaging.domain.role;

import com.github.punchat.messaging.domain.channel.BroadcastChannel;

import java.util.Set;

public interface RoleFinder {
    Role byId(Long id);

    Role owner(BroadcastChannel channel);

    Set<Role> byChannel(BroadcastChannel channel);
}
