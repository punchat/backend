package com.github.punchat.messaging.domain.member;

import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.role.Role;
import com.github.punchat.messaging.domain.user.User;

import java.util.Set;

public interface MemberService {
    Member createAdmin(BroadcastChannel channel, User user);

    Member create(Long userId, Long channelId, Long roleId);

    Member create(User user, BroadcastChannel channel, Role role);

    Set<Member> findByChannel(Long channelId);

    Member findByUserAndChannel(Long userId, String channelName);

    Member findByUserAndChannel(User user, BroadcastChannel channel);
}
