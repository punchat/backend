package com.github.punchat.am.domain.invite.workspace;

import com.github.punchat.am.domain.invite.workspace.dto.WorkspaceAccessCodeValidation;
import com.github.punchat.am.domain.invite.workspace.dto.WorkspaceAccessCodeValidationResult;
import com.github.punchat.am.domain.invite.workspace.dto.WorkspaceEmailValidation;
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
    public void create(@RequestBody java.lang.String email) {
       service.createWorkspaceInvite(email);
    }

    @PutMapping("/workspace/members/check")
    public WorkspaceEmailValidation check(@RequestBody java.lang.String email) {
        return service.checkWorkspaceInvite(email);
    }

    @PutMapping("/workspace/codes/request")
    @ResponseStatus(HttpStatus.OK)
    public void requestAccessCode(@RequestBody java.lang.String email) {
        service.requestAccessCode(email);
    }


    @PutMapping("/workspace/codes/check")
    public WorkspaceAccessCodeValidationResult checkAccessCode(@RequestBody WorkspaceAccessCodeValidation workspaceAccessCodeValidation) {
        return service.checkAccessCode(workspaceAccessCodeValidation);
    }
}
