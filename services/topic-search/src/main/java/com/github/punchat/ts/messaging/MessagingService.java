package com.github.punchat.ts.messaging;

import com.github.punchat.dto.messaging.message.BroadcastMessageResponse;
import com.github.punchat.dto.messaging.message.DirectMessageResponse;

public interface MessagingService {

    BroadcastMessageResponse getBroadcastMessage(Long messageId);

    DirectMessageResponse getDirectMessage(Long messageId);
}
