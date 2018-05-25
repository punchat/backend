package com.github.punchat.messaging.domain.member;

import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.role.Role;
import com.github.punchat.messaging.domain.user.User;

import java.util.Set;

public interface MemberService {
    Set<Member> getMembers(BroadcastChannel channel);

    Member createAdmin(BroadcastChannel channel, User user);

    Member create(User user, BroadcastChannel channel, Role role);

    void delete(Member member);

    Member getAuthorizedUserAsChannelMembers(BroadcastChannel channel);
}
