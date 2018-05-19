package com.github.punchat.am.domain.invite.workspace;

import com.github.punchat.dto.am.am.access.NewAccessCodeRequest;
import com.github.punchat.dto.am.am.access.WorkspaceAccessCodeValidation;
import com.github.punchat.dto.am.am.access.WorkspaceAccessCodeValidationResult;
import com.github.punchat.dto.am.am.invite.WorkspaceEmailValidation;
import com.github.punchat.dto.am.am.invite.WorkspaceEmailValidationResult;
import com.github.punchat.dto.am.am.invite.WorkspaceInvitation;

public interface WorkspaceInviteService {

    void createWorkspaceInvite(WorkspaceInvitation invitation);

    WorkspaceEmailValidationResult checkWorkspaceInvite(WorkspaceEmailValidation emailValidation);

    void requestAccessCode(NewAccessCodeRequest accessCodeRequest);

    WorkspaceAccessCodeValidationResult checkAccessCode(WorkspaceAccessCodeValidation accessCodeValidation);

//    void checkRegistrationData(WorkspaceRegistration registrationDto);
}
