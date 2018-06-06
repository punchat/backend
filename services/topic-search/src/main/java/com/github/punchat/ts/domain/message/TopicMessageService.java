package com.github.punchat.ts.domain.message;

import com.github.punchat.dto.ts.message.TopicBroadcastSearchRequest;
import com.github.punchat.dto.ts.message.TopicDirectSearchRequest;

import java.util.List;

public interface TopicMessageService {
    List<TopicMessage> searchBroadcast(TopicBroadcastSearchRequest request);
    List<TopicMessage> searchDirect(TopicDirectSearchRequest request);

    void saveBroadcastMessage(Long messageId, Long channelId);
    void saveDirectMessage(Long messageId);
}
