package com.github.punchat.messaging.domain.invite;

import com.github.punchat.dto.messaging.invite.ChannelInvitationResponse;

import java.util.List;

public interface ChannelInviteFacadeService {
    ChannelInvitationResponse getById(Long id);

    List<ChannelInvitationResponse> getAuthorizedUserInvites();

    List<ChannelInvitationResponse> getChannelInvites(Long id);

    ChannelInvitationResponse createChannelInvite(Long channelId, Long recipientId, Long roleId);

    ChannelInvitationResponse acceptInvitation(Long id);
}
