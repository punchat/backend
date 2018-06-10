package com.github.punchat.stomp.events

import com.github.punchat.dto.messaging.member.MemberResponse
import com.github.punchat.dto.messaging.message.DirectMessageResponse
import com.github.punchat.events.MessageReceivedEvent
import com.github.punchat.events.NewBroadcastMessageEvent
import com.github.punchat.events.NewDirectMessageEvent
import com.github.punchat.log.Trace
import com.github.punchat.stomp.messaging.MessagingService
import com.github.punchat.stomp.notifications.Notifier
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener

@Trace
@EnableBinding(Channels)
class Events {
    private final MessagingService service
    private final Notifier notifier

    Events(MessagingService service, Notifier notifier) {
        this.service = service
        this.notifier = notifier
    }

    @StreamListener(Channels.NEW_DIRECT_MESSAGE_EVENTS)
    void sendNewDirectMessageEvent(NewDirectMessageEvent event) {
        Long directMessageId = event.id
        DirectMessageResponse msg = service.getDirectMessageById(directMessageId)
        Long receiverUserId = msg.receiver.id
        notifier.awareUserReceivedMessage(receiverUserId,
                new MessageReceivedEvent(
                        type: MessageReceivedEvent.MessageType.DIRECT,
                        id: directMessageId
                )
        )
    }

    @StreamListener(Channels.NEW_BROADCAST_MESSAGE_EVENTS)
    void sendNewBroadcastMessageEvent(NewBroadcastMessageEvent event) {
        Long broadcastMessageId = event.messageId
        List<MemberResponse> members = service.getAllMembers(event.channelId)
        for (MemberResponse member : members) {
            notifier.awareUserReceivedMessage(member.user.id,
                    new MessageReceivedEvent(
                            type: MessageReceivedEvent.MessageType.BROADCAST,
                            id: broadcastMessageId
                    ))
        }
    }
}
