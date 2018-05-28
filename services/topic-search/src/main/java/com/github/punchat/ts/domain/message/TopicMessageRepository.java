package com.github.punchat.ts.domain.message;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicMessageRepository extends JpaRepository<TopicMessage, Long> {
    List<TopicMessage> findByChannelId(Long channelId);
}
