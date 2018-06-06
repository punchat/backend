package com.github.punchat.ts.domain.message;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicBroadcastMessageRepository extends JpaRepository<TopicBroadcastMessage, Long> {
    List<TopicBroadcastMessage> findByChannelId(Long channelId);
}
