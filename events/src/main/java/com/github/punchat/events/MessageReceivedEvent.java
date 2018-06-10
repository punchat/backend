package com.github.punchat.events;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageReceivedEvent {

    private MessageType type;
    private Long id;

    public enum MessageType {
        DIRECT, BROADCAST
    }
}
