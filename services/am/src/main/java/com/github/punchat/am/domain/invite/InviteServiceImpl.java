package com.github.punchat.am.domain.invite;

import com.github.punchat.am.domain.access.AccessCode;
import com.github.punchat.am.domain.access.AccessCodeService;
import com.github.punchat.am.events.AccessCodesBus;
import com.github.punchat.am.events.InvitesToChannelBus;
import com.github.punchat.am.events.InvitesToWorkspaceBus;
import com.github.punchat.am.events.MembersBus;
import com.github.punchat.events.AccessCodeGeneratedEvent;
import com.github.punchat.events.InviteToChannelEvent;
import com.github.punchat.events.InviteToWorkspaceEvent;
import com.github.punchat.events.NewMemberInChannelEvent;
import com.github.punchat.starter.uaa.client.context.AuthContext;
import org.springframework.stereotype.Service;
import com.github.punchat.am.id.IdService;

import java.time.Clock;
import java.time.LocalDateTime;

@Service
public class InviteServiceImpl implements InviteService {
    private final AuthContext authContext;
    private final ChannelInviteRepository channelInviteRepository;
    private final WorkspaceInviteRepository workspaceInviteRepository;
    private final AccessCodeService accessCodeService;
    private final IdService idService;
    private final InvitesToChannelBus invitesToChannelBus;
    private final InvitesToWorkspaceBus invitesToWorkspaceBus;
    private final MembersBus membersBus;
    private final AccessCodesBus accessCodesBus;

    public InviteServiceImpl(AuthContext authContext, ChannelInviteRepository channelInviteRepository,
                             WorkspaceInviteRepository workspaceInviteRepository,
                             AccessCodeService accessCodeService,
                             IdService idService,
                             InvitesToChannelBus invitesToChannelBus,
                             InvitesToWorkspaceBus invitesToWorkspaceBus,
                             MembersBus membersBus,
                             AccessCodesBus accessCodesBus) {
        this.authContext = authContext;
        this.channelInviteRepository = channelInviteRepository;
        this.workspaceInviteRepository = workspaceInviteRepository;
        this.accessCodeService = accessCodeService;
        this.idService = idService;
        this.invitesToChannelBus = invitesToChannelBus;
        this.invitesToWorkspaceBus = invitesToWorkspaceBus;
        this.membersBus = membersBus;
        this.accessCodesBus = accessCodesBus;
    }

    private Invite createInvite(Invite invite) {
        Long senderUserId = authContext.get().getUserInfo().get().getUserId();
        invite.setId(idService.next());
        invite.setSenderUserId(senderUserId);
        invite.setState(State.CREATED);
        return invite;
    }

    public ChannelInvite getInvite(Long recipientUserId, Long channelId) {
        return channelInviteRepository.findByRecipientUserIdAndChannelId(recipientUserId, channelId);
    }

    public WorkspaceInvite getInvite(String email) {
        return workspaceInviteRepository.findByEmail(email);
    }

    @Override
    public ChannelInvite createChannelInvite(ChannelInvite channelInvite) {
        channelInvite = (ChannelInvite) createInvite(channelInvite);
        invitesToChannelBus.publishInviteToChannel(new InviteToChannelEvent(
                channelInvite.getSenderUserId(), channelInvite.getRecipientUserId(),
                channelInvite.getChannelId(), LocalDateTime.now(Clock.systemUTC())));
        return channelInviteRepository.save(channelInvite);
    }

    @Override
    public ChannelInvite acceptChannelInvite(Long recipientUserId, Long channelId) {
        Long authUserId = authContext.get().getUserInfo().get().getUserId();
        if (authUserId.equals(recipientUserId)) {
            if (channelInviteRepository.existsByChannelId(channelId)) {
                ChannelInvite channelInvite = getInvite(recipientUserId, channelId);
                channelInvite.setState(State.ACCEPTED);
                membersBus.publishNewMemberInChannel(new NewMemberInChannelEvent(
                        channelInvite.getSenderUserId(), recipientUserId,
                        channelId, channelInvite.getId()));
                return channelInviteRepository.save(channelInvite);
            } else {
                throw new ChannelDoNotFound(channelId);
            }
        } else {
            throw new AccessFailureException();
        }
    }

    @Override
    public String getEmailState(String email) {
        if (workspaceInviteRepository.existsByEmail(email)) {
            return workspaceInviteRepository.findByEmail(email).getState().toString();
        } else {
            return "NOT CREATED";
        }
    }

    @Override
    public WorkspaceInvite createWorkspaceInvite(WorkspaceInvite workspaceInvite) {
        workspaceInvite = (WorkspaceInvite) createInvite(workspaceInvite);
        invitesToWorkspaceBus.publishInviteToWorkspace(new InviteToWorkspaceEvent(
                workspaceInvite.getSenderUserId(), workspaceInvite.getEmail(),
                LocalDateTime.now(Clock.systemUTC())));
        return workspaceInviteRepository.save(workspaceInvite);
    }

    @Override
    public WorkspaceInvite acceptWorkspaceInvite(String email) {
        if (workspaceInviteRepository.existsByEmail(email)) {
            WorkspaceInvite workspaceInvite = getInvite(email);
            workspaceInvite.setState(State.ANSWERED);
            workspaceInvite.setAccessCode(accessCodeService.generateAccessCode());
            workspaceInvite.setState(State.CODE_GENERATED);
            return workspaceInviteRepository.save(workspaceInvite);
        } else {
            throw new InviteDoNotFound(email);
        }
    }

    @Override
    public boolean checkAccessCode(String email, String code) {
        if (workspaceInviteRepository.existsByEmail(email)) {
            WorkspaceInvite workspaceInvite = getInvite(email);
            AccessCode accessCode = workspaceInvite.getAccessCode();
            AccessCode checkCode = new AccessCode(code, LocalDateTime.now(Clock.systemUTC()));
            if (accessCodeService.checkAccessCode(checkCode, accessCode)) {
                accessCodesBus.publishAccessCodeGenerated(new AccessCodeGeneratedEvent(
                        workspaceInvite.getEmail(), accessCode.getCode(), accessCode.getCreationTime()));
                return true;
            }
        }
        return false;
    }
}
