package com.github.punchat.messaging.domain.member;

import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Set<Member> findByChannel(BroadcastChannel channel);

    Optional<Member> findByUserAndChannel(User user, BroadcastChannel channel);

    boolean existsByUserAndChannel(User user, BroadcastChannel channel);

    @Query("select m from Member m where m.id in (:ids)")
    Set<Member> findByIds(@Param("ids") Set<Long> ids);
}
