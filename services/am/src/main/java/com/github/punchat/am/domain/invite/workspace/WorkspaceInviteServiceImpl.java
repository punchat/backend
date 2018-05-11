package com.github.punchat.am.domain.invite.workspace;

import com.github.punchat.am.domain.access.AccessCodeService;
import com.github.punchat.am.domain.invite.InviteService;
import com.github.punchat.am.domain.invite.State;
import com.github.punchat.am.events.EventBus;
import com.github.punchat.dto.*;
import com.github.punchat.events.*;
import org.springframework.stereotype.Service;

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
    public void createWorkspaceInvite(String email) {
        WorkspaceInvite invite = new WorkspaceInvite();
        if (workspaceInviteRepository.existsByEmail(email)) {
            invite = workspaceInviteRepository.findByEmail(email);
        } else {
            invite = (WorkspaceInvite) inviteService.createInvite(invite);
            invite.setEmail(email);
            workspaceInviteRepository.save(invite);
        }
        eventBus.publish(new InviteToWorkspaceEvent(
                invite.getSenderUserId(), invite.getEmail()));
    }

    @Override
    public WorkspaceEmailValidation checkWorkspaceInvite(String email) {
        if (workspaceInviteRepository.existsByEmail(email)) {
            WorkspaceInvite workspaceInvite = getInvite(email);
            workspaceInvite.setState(State.ANSWERED);
            workspaceInviteRepository.save(workspaceInvite);
            return new WorkspaceEmailValidation(email, WorkspaceEmailValidationResult.VALID);
        }
        return new WorkspaceEmailValidation(email, WorkspaceEmailValidationResult.INVALID);
    }

    @Override
    public void requestAccessCode(String email) {
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
    public WorkspaceAccessCodeValidationResult checkAccessCode(WorkspaceAccessCodeValidation validation) {
        WorkspaceInvite email = getInvite(validation.getEmail());
        AccessCodeValidationResult result =
                accessCodeService.checkAccessCode(email.getAccessCode(), validation);
        return new WorkspaceAccessCodeValidationResult(validation.getEmail(), validation.getCode(), result);
    }
}
