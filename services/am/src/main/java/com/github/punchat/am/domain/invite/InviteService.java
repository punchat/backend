package com.github.punchat.am.domain.invite;

public interface InviteService {

    ChannelInvite createChannelInvite(ChannelInvite invite);

    Invite getInvite(Long recipientUserId, Long channelId);

    Invite getInvite(String email);

    WorkspaceInvite createWorkspaceInvite(WorkspaceInvite invite);
}
