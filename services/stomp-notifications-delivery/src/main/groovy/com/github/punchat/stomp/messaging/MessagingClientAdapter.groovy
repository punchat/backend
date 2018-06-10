package com.github.punchat.stomp.messaging

import com.github.punchat.dto.messaging.member.MemberResponse
import com.github.punchat.dto.messaging.message.BroadcastMessageResponse
import com.github.punchat.dto.messaging.message.DirectMessageResponse
import com.github.punchat.log.Trace
import com.github.punchat.stomp.feign.MessagingClient
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Trace
@Service
@Profile("!test")
class MessagingClientAdapter implements MessagingService {
    private final MessagingClient client

    MessagingClientAdapter(MessagingClient client) {
        this.client = client
    }

    @Override
    DirectMessageResponse getDirectMessageById(Long id) {
        client.getDirectMessageById(id)
    }

    @Override
    BroadcastMessageResponse getBroadcastMessageById(Long id) {
        client.getBroadcastMessageById(id)
    }

    @Override
    List<MemberResponse> getAllMembers(Long channelId) {
        client.getAllMembers(channelId)
    }
}
