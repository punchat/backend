package com.github.punchat.am.domain.invite;

public interface InviteService {

    ChannelInvite createChannelInvite(ChannelInvite invite);

    ChannelInvite acceptChannelInvite(Long recipientUserId, Long channelId);

    ChannelInvite getInvite(Long recipientUserId, Long channelId);

    WorkspaceInvite createWorkspaceInvite(WorkspaceInvite invite);

    WorkspaceInvite acceptWorkspaceInvite(String email);

    WorkspaceInvite getInvite(String email);

    String getEmailState(String email);
}
