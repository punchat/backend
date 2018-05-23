package com.github.punchat.messaging.domain.invite;

import com.github.punchat.dto.messaging.invite.ChannelInvitationRequest;
import com.github.punchat.dto.messaging.invite.ChannelInvitationResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface ChannelInviteMapper {
    ChannelInvitationResponse toResponse(ChannelInvite invite);

    ChannelInvite fromRequest(ChannelInvitationRequest request);
}
