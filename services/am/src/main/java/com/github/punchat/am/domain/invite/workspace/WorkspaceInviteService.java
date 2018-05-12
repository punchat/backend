package com.github.punchat.am.domain.invite.workspace;

import com.github.punchat.dto.am.*;

public interface WorkspaceInviteService {

    void createWorkspaceInvite(WorkspaceInvitation invitation);

    WorkspaceEmailValidationResult checkWorkspaceInvite(WorkspaceEmailValidation emailValidation);

    void requestAccessCode(NewAccessCodeRequest accessCodeRequest);

    WorkspaceAccessCodeValidationResult checkAccessCode(WorkspaceAccessCodeValidation accessCodeValidation);

//    void checkRegistrationData(WorkspaceRegistration registrationDto);
}
