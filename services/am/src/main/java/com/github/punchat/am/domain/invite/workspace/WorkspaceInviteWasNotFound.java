package com.github.punchat.am.domain.invite.workspace;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WorkspaceInviteWasNotFound extends RuntimeException {

        public WorkspaceInviteWasNotFound(String email) {
            super(String.format("Invite to Workspace for Email: %s was not found", email));
        }
}
