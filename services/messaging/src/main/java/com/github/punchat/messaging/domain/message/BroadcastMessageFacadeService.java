package com.github.punchat.messaging.domain.message;

import com.github.punchat.dto.messaging.message.BroadcastMessageRequest;
import com.github.punchat.dto.messaging.message.BroadcastMessageResponse;

import java.util.List;

public interface BroadcastMessageFacadeService {
    BroadcastMessageResponse getById(Long id);

    BroadcastMessageResponse create(BroadcastMessageRequest payload);

    List<BroadcastMessageResponse> getLast(Long channelId, int limit);

    List<BroadcastMessageResponse> getBefore(Long channelId, Long anchor, int limit);

    List<BroadcastMessageResponse> getAfter(Long channelId, Long anchor, int limit);
}
