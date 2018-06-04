package com.github.punchat.stomp.notifications

import com.github.punchat.events.MessageReceivedEvent
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Service

@Service
class NotifierImpl implements Notifier {
    private SimpMessageSendingOperations sender

    NotifierImpl(SimpMessageSendingOperations sender) {
        this.sender = sender
    }

    @Override
    void awareUserReceivedMessage(Long userId, MessageReceivedEvent event) {
        String destination = "/topic/notifications/$userId"
        sender.convertAndSend(destination, event)
    }
}
