package com.github.punchat.stomp.messaging

import com.github.punchat.dto.messaging.member.MemberResponse
import com.github.punchat.dto.messaging.message.BroadcastMessageResponse
import com.github.punchat.dto.messaging.message.DirectMessageResponse

interface MessagingService {
    DirectMessageResponse getDirectMessageById(Long id)

    BroadcastMessageResponse getBroadcastMessageById(Long id)

    List<MemberResponse> getAllMembers(Long channelId)
}
