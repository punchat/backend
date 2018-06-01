package com.github.punchat.ts.domain.message;

import com.github.punchat.dto.ts.message.TopicSearchRequest;

import java.util.List;

public interface TopicMessageService {
    List<TopicMessage> search(TopicSearchRequest request);

    void saveMessage(Long messageId, Long channelId);
}
