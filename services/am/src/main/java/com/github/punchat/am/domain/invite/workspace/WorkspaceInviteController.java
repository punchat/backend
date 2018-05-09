package com.github.punchat.am.domain.invite.workspace;

import com.github.punchat.am.domain.invite.workspace.dto.AccessCodeValidation;
import com.github.punchat.am.domain.invite.workspace.dto.EmailValidation;
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
    public WorkspaceInvite create(@RequestBody WorkspaceInvite invite) {
        return service.createWorkspaceInvite(invite);
    }

    @PutMapping("/workspace/members/check")
    public EmailValidation check(@RequestBody String email) {
        return service.checkWorkspaceInvite(email);
    }

    @PutMapping("/workspace/codes/request")
    @ResponseStatus(HttpStatus.OK)
    public WorkspaceInvite requestAccessCode(@RequestBody String email) {
        return service.requestAccessCode(email);
    }


    @PutMapping("/workspace/codes/check")
    public AccessCodeValidation checkAccessCode(@RequestBody AccessCodeValidation accessCodeValidation) {
        return service.checkAccessCode(accessCodeValidation);
    }
}
