package com.github.punchat.am.domain.invite.workspace;

import com.github.punchat.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class WorkspaceInviteController {
    private final WorkspaceInviteService service;

    public WorkspaceInviteController(WorkspaceInviteService service) {
        this.service = service;
    }

    @PostMapping("/workspace/members/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createWorkspaceInvite(@RequestBody WorkspaceInvitation invitation) {
       service.createWorkspaceInvite(invitation);
    }

    @PutMapping("/workspace/members/check")
    public WorkspaceEmailValidationResult checkWorkspaceInvite(@RequestBody WorkspaceEmailValidation email) {
        return service.checkWorkspaceInvite(email);
    }

    @PutMapping("/workspace/codes/request")
    @ResponseStatus(HttpStatus.OK)
    public void requestAccessCode(@RequestBody NewAccessCodeRequest email) {
        service.requestAccessCode(email);
    }


    @PutMapping("/workspace/codes/check")
    public WorkspaceAccessCodeValidationResult checkAccessCode(@RequestBody WorkspaceAccessCodeValidation accessCode) {
        return service.checkAccessCode(accessCode);
    }
}
