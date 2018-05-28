package com.github.punchat.messaging.domain.message;

import com.github.punchat.dto.messaging.message.DirectMessageRequest;
import com.github.punchat.dto.messaging.message.DirectMessageResponse;

import java.util.List;

public interface DirectMessageFacadeService {
    DirectMessageResponse getById(Long id);

    DirectMessageResponse create(DirectMessageRequest payload);

    List<DirectMessageResponse> getLast(Long user1Id, Long user2Id, int limit);

    List<DirectMessageResponse> getBefore(Long user1Id, Long user2Id, Long anchor, int limit);

    List<DirectMessageResponse> getAfter(Long user1Id, Long user2Id, Long anchor, int limit);
}
