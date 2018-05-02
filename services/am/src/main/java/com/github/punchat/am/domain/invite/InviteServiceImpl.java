package com.github.punchat.am.domain.invite;

import com.github.punchat.starter.uaa.client.context.AuthContext;
import org.springframework.stereotype.Service;
import com.github.punchat.am.id.IdService;

@Service
public class InviteServiceImpl implements InviteService {
    private final AuthContext authContext;
    private final ChannelInviteRepository channelInviteRepository;
    private final WorkspaceInviteRepository workspaceInviteRepository;
    private final IdService idService;

    public InviteServiceImpl(AuthContext authContext,
                             ChannelInviteRepository channelInviteRepository,
                             WorkspaceInviteRepository workspaceInviteRepository,
                             IdService idService) {
        this.authContext = authContext;
        this.channelInviteRepository = channelInviteRepository;
        this.workspaceInviteRepository = workspaceInviteRepository;
        this.idService = idService;
    }

    private Invite createInvite(Invite invite) {
        Long senderUserId = authContext.get().getUserInfo().get().getUserId();
        invite.setId(idService.next());
        invite.setSenderUserId(senderUserId);
        invite.setState(State.CREATED);
        return invite;
    }

    @Override
    public Invite getInvite(Long recipientUserId, Long channelId) {
        return channelInviteRepository.findByRecipientUserIdAndChannelId(recipientUserId, channelId);
    }

    @Override
    public ChannelInvite createChannelInvite(ChannelInvite invite) {
        invite = (ChannelInvite) createInvite(invite);
        invite = channelInviteRepository.save(invite);
        return invite;
    }

    @Override
    public Invite getInvite(String email) {

        return workspaceInviteRepository.findByEmail(email);
    }

    @Override
    public WorkspaceInvite createWorkspaceInvite(WorkspaceInvite invite) {
        invite = (WorkspaceInvite) createInvite(invite);
        invite = workspaceInviteRepository.save(invite);
        return invite;
    }
}
