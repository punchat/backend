package com.github.punchat.stomp.notifications

import com.github.punchat.events.MessageReceivedEvent


interface Notifier {
    void awareUserReceivedMessage(Long userId, MessageReceivedEvent event)
}