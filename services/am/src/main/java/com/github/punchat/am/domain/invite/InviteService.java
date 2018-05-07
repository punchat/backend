package com.github.punchat.am.domain.invite;

public interface InviteService {

    ChannelInvite createChannelInvite(ChannelInvite invite);

    ChannelInvite acceptChannelInvite(Long recipientUserId, Long channelId);

    WorkspaceInvite createWorkspaceInvite(WorkspaceInvite invite);

    WorkspaceInvite acceptWorkspaceInvite(String email);

    boolean checkAccessCode(String email, String code);

    String getEmailState(String email);
}
