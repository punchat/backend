package com.github.punchat.am.domain.invite.workspace;

import com.github.punchat.am.domain.invite.workspace.dto.AccessCodeValidation;
import com.github.punchat.am.domain.invite.workspace.dto.EmailValidation;

public interface WorkspaceInviteService {

    WorkspaceInvite createWorkspaceInvite(WorkspaceInvite invite);

    EmailValidation checkWorkspaceInvite(String email);

    WorkspaceInvite requestAccessCode(String email);

    AccessCodeValidation checkAccessCode(AccessCodeValidation accessCodeValidation);
}
