package com.github.punchat.am.domain.invite.workspace;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WorkspaceInviteDoNotFound extends RuntimeException {

        public WorkspaceInviteDoNotFound(String email) {
            super(String.format("Invite to Workspace for Email: %s do not found", email));
        }
}
