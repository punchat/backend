package com.github.punchat.messaging.domain.member;

import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUser(User user);

    Set<Member> findByChannel(BroadcastChannel channel);
}
