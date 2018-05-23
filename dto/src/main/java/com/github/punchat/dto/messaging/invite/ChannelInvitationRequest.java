package com.github.punchat.dto.messaging.invite;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChannelInvitationRequest {
    private Long recipientId;
    private Long roleId;
}
