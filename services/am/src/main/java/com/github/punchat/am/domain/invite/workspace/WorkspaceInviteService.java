package com.github.punchat.am.domain.invite.workspace;

import com.github.punchat.dto.*;

public interface WorkspaceInviteService {

    void createWorkspaceInvite(String email);

    WorkspaceEmailValidation checkWorkspaceInvite(String email);

    void requestAccessCode(String email);

    WorkspaceAccessCodeValidationResult checkAccessCode(WorkspaceAccessCodeValidation workspaceAccessCodeValidation);
}
