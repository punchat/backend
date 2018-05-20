package com.github.punchat.messaging.domain.invite;

public interface ChannelInviteService {

    ChannelInvite createChannelInvite(String channelName, Long userId);

    ChannelInvite acceptChannelInvite(String channelName);
}
