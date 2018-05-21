package com.github.punchat.messaging.domain.invite;

import java.util.Set;

public interface ChannelInviteService {

    Set<Long> getUserChannelsInvited(Long userId);

    ChannelInvite createChannelInvite(String channelName, Long userId, Long roleId);

    ChannelInvite acceptChannelInvite(String channelName);
}
