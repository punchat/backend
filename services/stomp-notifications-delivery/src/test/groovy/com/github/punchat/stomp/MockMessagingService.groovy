package com.github.punchat.stomp

import com.github.punchat.dto.messaging.channel.BroadcastChannelResponse
import com.github.punchat.dto.messaging.member.MemberResponse
import com.github.punchat.dto.messaging.message.BroadcastMessageResponse
import com.github.punchat.dto.messaging.message.DirectMessageResponse
import com.github.punchat.stomp.messaging.MessagingService

import java.util.function.Function

class MockMessagingService implements MessagingService {
    Function<Long, DirectMessageResponse> directMessages = { id -> new DirectMessageResponse() }
    Function<Long, BroadcastMessageResponse> broadcastMessages = { id -> new BroadcastChannelResponse() }
    Function<Long, List<MemberResponse>> members = { id -> new ArrayList<>() }

    @Override
    DirectMessageResponse getDirectMessageById(Long id) {
        directMessages.apply(id)
    }

    @Override
    BroadcastMessageResponse getBroadcastMessageById(Long id) {
        broadcastMessages.apply(id)
    }

    @Override
    List<MemberResponse> getAllMembers(Long channelId) {
        members.apply(channelId)
    }
}
