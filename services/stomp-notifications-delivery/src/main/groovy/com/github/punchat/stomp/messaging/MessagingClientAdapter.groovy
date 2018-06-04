package com.github.punchat.stomp.messaging

import com.github.punchat.dto.messaging.member.MemberResponse
import com.github.punchat.dto.messaging.message.BroadcastMessageResponse
import com.github.punchat.dto.messaging.message.DirectMessageResponse
import com.github.punchat.stomp.feign.MessagingClient
import groovy.transform.Immutable
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile("!test")
@Immutable
class MessagingClientAdapter implements MessagingService {
    private MessagingClient client

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
