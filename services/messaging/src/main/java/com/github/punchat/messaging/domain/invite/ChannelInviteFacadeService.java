package com.github.punchat.messaging.domain.invite;

import java.util.Set;

public interface ChannelInviteFacadeService {
    Set<ChannelInvite> getAuthorizedUserInvitations();

    ChannelInvite createChannelInvitation(Long channelId, Long recipientId, Long roleId);

    ChannelInvite acceptChannelInvitation(Long channelId);

    ChannelInvite acceptChannelInvitation(String channelName);
}
