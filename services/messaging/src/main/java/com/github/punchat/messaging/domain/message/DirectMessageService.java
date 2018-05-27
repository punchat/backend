package com.github.punchat.messaging.domain.message;

import com.github.punchat.dto.messaging.message.DirectMessageRequest;

public interface DirectMessageService {
    DirectMessage create(DirectMessageRequest payload);
}
