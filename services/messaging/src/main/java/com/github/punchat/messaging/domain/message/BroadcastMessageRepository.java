package com.github.punchat.messaging.domain.message;

import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface BroadcastMessageRepository extends JpaRepository<BroadcastMessage, Long> {
    @Query("select msg from BroadcastMessage msg where msg.channel = :channel order by msg.createdOn desc")
    Page<BroadcastMessage> findLast(@Param("channel") BroadcastChannel channel, Pageable pageable);

    @Query("select msg from BroadcastMessage msg where msg.channel = :channel and msg.createdOn < :anchor order by msg.createdOn desc")
    Page<BroadcastMessage> findBefore(@Param("channel") BroadcastChannel channel, @Param("anchor") LocalDateTime anchor, Pageable pageable);

    @Query("select msg from BroadcastMessage msg where msg.channel = :channel and msg.createdOn > :anchor order by msg.createdOn desc")
    Page<BroadcastMessage> findAfter(@Param("channel") BroadcastChannel channel, @Param("anchor") LocalDateTime anchor, Pageable pageable);
}
