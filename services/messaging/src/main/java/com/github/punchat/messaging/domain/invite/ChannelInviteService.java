package com.github.punchat.messaging.domain.invite;

import java.util.Set;

public interface ChannelInviteService {

    ChannelInvite getInvite(Long recipientUserId, Long channelId);

    ChannelInvite createChannelInvite(Long recipientUserId, Long channelId);

    Set<Long> getUserChannelsInvited(Long userId);

    ChannelInvite acceptChannelInvite(Long channelId);
}
