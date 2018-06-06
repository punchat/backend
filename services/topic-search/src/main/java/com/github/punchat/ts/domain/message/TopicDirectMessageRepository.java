package com.github.punchat.ts.domain.message;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicDirectMessageRepository extends JpaRepository<TopicDirectMessage, Long> {
    List<TopicDirectMessage> findBySenderIdAndReceiverId(Long senderId, Long receiverId);
}
