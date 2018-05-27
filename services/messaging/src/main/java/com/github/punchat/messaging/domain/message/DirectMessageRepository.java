package com.github.punchat.messaging.domain.message;

import com.github.punchat.messaging.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface DirectMessageRepository extends JpaRepository<DirectMessage, Long> {
    @Query("select msg from DirectMessage msg where msg.channel.user = :user1 or msg.channel.user = :user2 order by msg.createdOn desc")
    Page<DirectMessage> findLast(@Param("user1") User user1, @Param("user2") User user2, Pageable pageable);

    @Query("select msg from DirectMessage msg where (msg.channel.user = :user1 or msg.channel.user = :user2) and msg.createdOn < :anchor order by msg.createdOn desc")
    Page<DirectMessage> findBefore(@Param("user1") User user1, @Param("user2") User user2, @Param("anchor") LocalDateTime anchor, Pageable pageable);

    @Query("select msg from DirectMessage msg where (msg.channel.user = :user1 or msg.channel.user = :user2) and msg.createdOn > :anchor order by msg.createdOn desc")
    Page<DirectMessage> findAfter(@Param("user1") User user1, @Param("user2") User user2, @Param("anchor") LocalDateTime anchor, Pageable pageable);
}
