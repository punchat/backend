package com.github.punchat.messaging.domain.invite;

import com.github.punchat.dto.messaging.invite.ChannelInvitationResponse;

import java.util.Set;

public interface ChannelInviteFacadeService {
    ChannelInvitationResponse getById(Long id);

    Set<ChannelInvitationResponse> getAuthorizedUserInvites();

    Set<ChannelInvitationResponse> getChannelInvites(Long id);

    ChannelInvitationResponse createChannelInvite(Long channelId, Long recipientId, Long roleId);

    ChannelInvitationResponse acceptInvitation(Long id);
}
