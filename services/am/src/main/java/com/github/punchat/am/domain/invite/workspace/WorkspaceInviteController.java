package com.github.punchat.am.domain.invite.workspace;

import com.github.punchat.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value="workspace", description="Operations for Workspace invitation")
public class WorkspaceInviteController {
    private final WorkspaceInviteService service;

    public WorkspaceInviteController(WorkspaceInviteService service) {
        this.service = service;
    }

    @ApiOperation(value = "Create new invite to workspace")
    @PostMapping(value = "/workspace/members/", produces = "application/json")
    public ResponseEntity createWorkspaceInvite(@RequestBody WorkspaceInvitation invitation) {
       service.createWorkspaceInvite(invitation);
       return new ResponseEntity("Invite to workspace created successfully", HttpStatus.CREATED);
    }

    @ApiOperation(value = "Validation workspace invite by email")
    @PutMapping(value = "/workspace/members/check", produces = "application/json")
    public WorkspaceEmailValidationResult checkWorkspaceInvite(@RequestBody WorkspaceEmailValidation email) {
        return service.checkWorkspaceInvite(email);
    }

    @ApiOperation(value = "Request new access code for invitation")
    @PutMapping(value = "/workspace/codes/request", produces = "application/json")
    public ResponseEntity requestAccessCode(@RequestBody NewAccessCodeRequest email) {
        service.requestAccessCode(email);
        return new ResponseEntity("Access code requested successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Check access code for invitation")
    @PutMapping(value = "/workspace/codes/check", produces = "application/json")
    public WorkspaceAccessCodeValidationResult checkAccessCode(@RequestBody WorkspaceAccessCodeValidation accessCode) {
        return service.checkAccessCode(accessCode);
    }
}
