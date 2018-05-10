package com.github.punchat.am.domain.invite.workspace;

import com.github.punchat.am.domain.invite.workspace.dto.WorkspaceAccessCodeValidation;
import com.github.punchat.am.domain.invite.workspace.dto.WorkspaceAccessCodeValidationResult;
import com.github.punchat.am.domain.invite.workspace.dto.WorkspaceEmailValidation;

public interface WorkspaceInviteService {

    void createWorkspaceInvite(String email);

    WorkspaceEmailValidation checkWorkspaceInvite(String email);

    void requestAccessCode(String email);

    WorkspaceAccessCodeValidationResult checkAccessCode(WorkspaceAccessCodeValidation workspaceAccessCodeValidation);
}
