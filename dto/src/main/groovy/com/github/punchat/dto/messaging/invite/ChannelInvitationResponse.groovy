package com.github.punchat.dto.messaging.invite

import com.github.punchat.dto.messaging.channel.BroadcastChannelResponse
import com.github.punchat.dto.messaging.member.MemberDto
import groovy.transform.ToString

@ToString
class ChannelInvitationResponse {
    Long id
    MemberDto sender
    BroadcastChannelResponse channel
    MemberDto recipient
}
