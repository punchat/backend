package com.github.punchat.ts;

import com.github.punchat.dto.messaging.message.BroadcastMessageResponse;
import com.github.punchat.dto.messaging.message.DirectMessageResponse;
import com.github.punchat.dto.messaging.user.UserDto;
import com.github.punchat.ts.messaging.MessagingService;

public class MockMessagingService implements MessagingService {

    @Override
    public BroadcastMessageResponse getBroadcastMessage(Long messageId) {
        return new BroadcastMessageResponse(null, null, "Hello", null, null);
    }

    @Override
    public DirectMessageResponse getDirectMessage(Long messageId) {
        return new DirectMessageResponse(null, new UserDto(1L), new UserDto(2L), "Hello");
    }
}
