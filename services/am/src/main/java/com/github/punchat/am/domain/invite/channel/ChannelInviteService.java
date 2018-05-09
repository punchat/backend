package com.github.punchat.am.domain.invite.channel;

public interface ChannelInviteService {

    ChannelInvite createChannelInvite(Long recipientUserId, Long channelId);

    ChannelInvite acceptChannelInvite(Long channelId);
}
