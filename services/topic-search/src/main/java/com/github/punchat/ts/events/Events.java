package com.github.punchat.ts.events;

import com.github.punchat.events.NewBroadcastMessageEvent;
import com.github.punchat.log.Trace;
import com.github.punchat.ts.domain.message.TopicMessageService;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@Trace
@EnableBinding(Channels.class)
@AllArgsConstructor
public class Events {
    private final TopicMessageService messageService;

    @StreamListener(Channels.NEW_BROADCAST_MESSAGE_EVENTS)
    public void createUser(NewBroadcastMessageEvent event) {
        messageService.saveMessage(event.getMessageId(), event.getChannelId());
    }
}

