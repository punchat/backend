package com.github.punchat.messaging.domain.member;

import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.user.User;

import java.util.Set;

public interface MemberFinder {
    Member byId(Long id);

    Set<Member> byIds(Set<Long> ids);

    Member byUserAndChannel(User user, BroadcastChannel channel);
}
