package com.github.punchat.messaging.domain.member;

import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.user.User;

public interface MemberFinder {
    Member byId(Long id);

    Member byUserAndChannel(User user, BroadcastChannel channel);
}
