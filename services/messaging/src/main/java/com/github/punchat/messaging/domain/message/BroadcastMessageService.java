package com.github.punchat.messaging.domain.message;

import com.github.punchat.dto.messaging.message.BroadcastMessageRequest;

public interface BroadcastMessageService {
    BroadcastMessage create(BroadcastMessageRequest payload);
}
