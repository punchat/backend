package com.github.punchat.am.domain.invite.workspace;

import com.github.punchat.am.domain.access.AccessCodeService;
import com.github.punchat.am.domain.invite.InviteService;
import com.github.punchat.am.domain.invite.State;
import com.github.punchat.am.events.EventBus;
import com.github.punchat.dto.*;
import com.github.punchat.events.*;
import com.github.punchat.log.Trace;
import org.springframework.stereotype.Service;

@Trace
@Service
public class WorkspaceInviteServiceImpl implements WorkspaceInviteService {
    private final WorkspaceInviteRepository workspaceInviteRepository;
    private final InviteService inviteService;
    private final AccessCodeService accessCodeService;
    private final EventBus eventBus;

    public WorkspaceInviteServiceImpl(WorkspaceInviteRepository workspaceInviteRepository,
                                      InviteService inviteService,
                                      AccessCodeService accessCodeService,
                                      EventBus eventBus) {
        this.workspaceInviteRepository = workspaceInviteRepository;
        this.inviteService = inviteService;
        this.accessCodeService = accessCodeService;
        this.eventBus = eventBus;
    }

    public WorkspaceInvite getInvite(java.lang.String email) {
        return workspaceInviteRepository.findByEmail(email);
    }

    @Override
    public void createWorkspaceInvite(WorkspaceInvitation invitation) {
        String email = invitation.getEmail();
        WorkspaceInvite workspaceInvite = new WorkspaceInvite();
        if (workspaceInviteRepository.existsByEmail(email)) {
            workspaceInvite = workspaceInviteRepository.findByEmail(email);
        } else {
            workspaceInvite = (WorkspaceInvite) inviteService.createInvite(workspaceInvite);
            workspaceInvite.setEmail(email);
            workspaceInviteRepository.save(workspaceInvite);
        }
        eventBus.publish(new InviteToWorkspaceEvent(
                workspaceInvite.getSenderUserId(), invitation.getEmail()));
    }

    @Override
    public WorkspaceEmailValidationResult checkWorkspaceInvite(WorkspaceEmailValidation emailValidation) {
        String email = emailValidation.getEmail();
        if (workspaceInviteRepository.existsByEmail(email)) {
            WorkspaceInvite workspaceInvite = getInvite(email);
            workspaceInvite.setState(State.ANSWERED);
            workspaceInviteRepository.save(workspaceInvite);
            return new WorkspaceEmailValidationResult(email, EmailValidationResult.VALID);
        }
        return new WorkspaceEmailValidationResult(email, EmailValidationResult.INVALID);
    }

    @Override
    public void requestAccessCode(NewAccessCodeRequest accessCodeRequest) {
        String email = accessCodeRequest.getEmail();
        if (!workspaceInviteRepository.existsByEmail(email)) {
            throw new WorkspaceInviteDoNotFound(email);
        }
        WorkspaceInvite workspaceInvite = getInvite(email);
        if (workspaceInvite.getAccessCode() != null) {
            workspaceInvite.setAccessCode(
                       accessCodeService.refreshAccessCode(workspaceInvite.getAccessCode()));
        }
        workspaceInvite.setAccessCode(accessCodeService.generateAccessCode());
        eventBus.publish(new AccessCodeGeneratedEvent(
                workspaceInvite.getEmail(),
                workspaceInvite.getAccessCode().getCode()));
        workspaceInviteRepository.save(workspaceInvite);
    }

    @Override
    public WorkspaceAccessCodeValidationResult checkAccessCode(WorkspaceAccessCodeValidation accessCodeValidation) {
        WorkspaceInvite email = getInvite(accessCodeValidation.getEmail());
        AccessCodeValidationResult result =
                accessCodeService.checkAccessCode(email.getAccessCode(), accessCodeValidation);
        return new WorkspaceAccessCodeValidationResult(accessCodeValidation.getEmail(),
                accessCodeValidation.getCode(), result);
    }
}
