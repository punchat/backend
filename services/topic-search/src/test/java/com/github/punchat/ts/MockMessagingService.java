package com.github.punchat.ts;

import com.github.punchat.ts.messaging.MessagingService;

public class MockMessagingService implements MessagingService {

    @Override
    public String getText(Long messageId) {
        return "Hello!";
    }
}
