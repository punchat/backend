package com.github.punchat.am.domain.invite;

import com.github.punchat.am.domain.access.AccessCodeService;
import com.github.punchat.am.events.EventBus;
import com.github.punchat.events.AccessCodeGeneratedEvent;
import com.github.punchat.events.InviteToChannelEvent;
import com.github.punchat.events.InviteToWorkspaceEvent;
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
    private final EventBus eventBus;

    public InviteServiceImpl(AuthContext authContext,
                             ChannelInviteRepository channelInviteRepository,
                             WorkspaceInviteRepository workspaceInviteRepository,
                             IdService idService,
                             EventBus eventBus,
                             AccessCodeService accessCodeService) {
        this.authContext = authContext;
        this.channelInviteRepository = channelInviteRepository;
        this.workspaceInviteRepository = workspaceInviteRepository;
        this.idService = idService;
        this.eventBus = eventBus;
        this.accessCodeService = accessCodeService;
    }

    private Invite createInvite(Invite invite) {
        Long senderUserId = authContext.get().getUserInfo().get().getUserId();
        invite.setId(idService.next());
        invite.setSenderUserId(senderUserId);
        invite.setState(State.CREATED);
        return invite;
    }

    @Override
    public ChannelInvite getInvite(Long recipientUserId, Long channelId) {
        return channelInviteRepository.findByRecipientUserIdAndChannelId(recipientUserId, channelId);
    }

    @Override
    public ChannelInvite createChannelInvite(ChannelInvite channelInvite) {
        channelInvite = (ChannelInvite) createInvite(channelInvite);
        eventBus.publishInviteToChannelCreated(new InviteToChannelEvent(channelInvite.getSenderUserId(),
                channelInvite.getRecipientUserId(), channelInvite.getChannelId(),
                channelInvite.getState().toString(), LocalDateTime.now(Clock.systemUTC())));
        return channelInviteRepository.save(channelInvite);
    }

    @Override
    public ChannelInvite acceptChannelInvite(Long recipientUserId, Long channelId) {
        Long authUserId = authContext.get().getUserInfo().get().getUserId();
        if (authUserId.equals(recipientUserId)) {
            if (channelInviteRepository.existsByChannelId(channelId)) {
                ChannelInvite channelInvite = getInvite(recipientUserId, channelId);
                channelInvite.setState(State.ACCEPTED);
                eventBus.publishInviteToChannelAccepted(new InviteToChannelEvent(channelInvite.getSenderUserId(),
                        recipientUserId, channelId,
                        channelInvite.getState().toString(), LocalDateTime.now(Clock.systemUTC())));
                return channelInviteRepository.save(channelInvite);
            } else {
                throw new ChannelDoNotFound(channelId);
            }
        } else {
            throw new AccessFailureException();
        }
    }

    @Override
    public WorkspaceInvite getInvite(String email) {
        return workspaceInviteRepository.findByEmail(email);
    }

    @Override
    public String getEmailState(String email) {
        if (workspaceInviteRepository.existsByEmail(email)) {
            return workspaceInviteRepository.findByEmail(email).getState().toString();
        }
        else {
            return "NOT CREATED";
        }
    }

    @Override
    public WorkspaceInvite createWorkspaceInvite(WorkspaceInvite workspaceInvite) {
        workspaceInvite = (WorkspaceInvite) createInvite(workspaceInvite);
        eventBus.publishInviteToWorkspaceCreated(new InviteToWorkspaceEvent(workspaceInvite.getSenderUserId(),
                workspaceInvite.getEmail(), workspaceInvite.getState().toString(),
                LocalDateTime.now(Clock.systemUTC())));
        return workspaceInviteRepository.save(workspaceInvite);
    }

    @Override
    public WorkspaceInvite acceptWorkspaceInvite(String email) {
        if (workspaceInviteRepository.existsByEmail(email)) {
            WorkspaceInvite workspaceInvite = getInvite(email);
            workspaceInvite.setState(State.ANSWERED);
            workspaceInvite.setAccessCode(accessCodeService.generateAccessCode(email));
            workspaceInvite.setState(State.CODE_GENERATED);
            eventBus.publishAccessCodeGenerated(new AccessCodeGeneratedEvent(workspaceInvite.getEmail(),
                    workspaceInvite.getAccessCode().getCode(), workspaceInvite.getAccessCode().getCreationTime()));
            return workspaceInviteRepository.save(workspaceInvite);
        } else {
            throw new InviteDoNotFound(email);
        }
    }
}
