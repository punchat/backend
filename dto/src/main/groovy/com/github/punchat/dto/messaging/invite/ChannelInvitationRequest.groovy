package com.github.punchat.dto.messaging.invite

import groovy.transform.ToString

@ToString
class ChannelInvitationRequest {
    Long recipientId
    Long roleId
}
