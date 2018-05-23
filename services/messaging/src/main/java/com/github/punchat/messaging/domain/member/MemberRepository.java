package com.github.punchat.messaging.domain.member;

import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Set<Member> findByChannel(BroadcastChannel channel);

    Optional<Member> findByUserAndChannel(User user, BroadcastChannel channel);

    boolean existsByUserAndChannel(User user, BroadcastChannel channel);
}
