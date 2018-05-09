package com.github.punchat.am.domain.invite.workspace;

import com.github.punchat.am.domain.access.AccessCodeService;
import com.github.punchat.am.domain.invite.InviteService;
import com.github.punchat.am.domain.invite.State;
import com.github.punchat.am.domain.invite.workspace.dto.AccessCodeValidation;
import com.github.punchat.am.domain.invite.workspace.dto.EmailValidation;
import com.github.punchat.am.domain.invite.workspace.dto.EmailValidationResult;
import com.github.punchat.am.events.EventBus;
import com.github.punchat.events.AccessCodeGeneratedEvent;
import com.github.punchat.events.InviteToWorkspaceEvent;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;

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

    public WorkspaceInvite getInvite(String email) {
        return workspaceInviteRepository.findByEmail(email);
    }

    @Override
    public WorkspaceInvite createWorkspaceInvite(WorkspaceInvite invite) {
        WorkspaceInvite workspaceInvite;
        if (workspaceInviteRepository.existsByEmail(invite.getEmail())) {
            workspaceInvite = workspaceInviteRepository.findByEmail(invite.getEmail());
        } else {
            workspaceInvite = (WorkspaceInvite) inviteService.createInvite(invite);
            workspaceInvite.setEmail(invite.getEmail());
        }
        eventBus.publish(new InviteToWorkspaceEvent(
                workspaceInvite.getSenderUserId(), workspaceInvite.getEmail(),
                LocalDateTime.now(Clock.systemUTC())));
        return workspaceInviteRepository.save(workspaceInvite);
    }

    @Override
    public EmailValidation checkWorkspaceInvite(String email) {
        if (workspaceInviteRepository.existsByEmail(email)) {
            WorkspaceInvite workspaceInvite = getInvite(email);
            workspaceInvite.setState(State.ANSWERED);
            workspaceInviteRepository.save(workspaceInvite);
            return new EmailValidation(email, EmailValidationResult.VALID);
        }
        return new EmailValidation(email, EmailValidationResult.INVALID);
    }

    @Override
    public WorkspaceInvite requestAccessCode(String email) {
        WorkspaceInvite workspaceInvite = getInvite(email);
        if (workspaceInvite.getAccessCode() != null) {
            workspaceInvite.setAccessCode(
                       accessCodeService.refreshAccessCode(workspaceInvite.getAccessCode()));
        }
        workspaceInvite.setAccessCode(accessCodeService.generateAccessCode());
        eventBus.publish(new AccessCodeGeneratedEvent(
                workspaceInvite.getEmail(),
                workspaceInvite.getAccessCode().getCode(),
                workspaceInvite.getAccessCode().getCreationTime()));
        return workspaceInviteRepository.save(workspaceInvite);
    }

    @Override
    public AccessCodeValidation checkAccessCode(AccessCodeValidation accessCodeValidation) {
        WorkspaceInvite workspaceInvite = getInvite(accessCodeValidation.getEmail());
        accessCodeValidation.setAccessCodeValidationResult(
                accessCodeService.checkAccessCode(
                        workspaceInvite.getAccessCode(), accessCodeValidation));
        return accessCodeValidation;
    }
}
