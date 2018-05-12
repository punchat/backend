package com.github.punchat.am.domain.invite.workspace;

import com.github.punchat.dto.am.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value="workspace", description="Operations for Workspace invitation")
public class WorkspaceInviteController {
    private final WorkspaceInviteService service;

    public WorkspaceInviteController(WorkspaceInviteService service) {
        this.service = service;
    }

    @ApiOperation(value = "Create new invite to workspace")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/workspace/members/")
    public void createWorkspaceInvite(@RequestBody WorkspaceInvitation invitation) {
       service.createWorkspaceInvite(invitation);
    }

    @ApiOperation(value = "Validation workspace invite by email")
    @PutMapping(value = "/workspace/members/check")
    public WorkspaceEmailValidationResult checkWorkspaceInvite(@RequestBody WorkspaceEmailValidation email) {
        return service.checkWorkspaceInvite(email);
    }

    @ApiOperation(value = "Request new access code for invitation")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/workspace/codes/request")
    public void requestAccessCode(@RequestBody NewAccessCodeRequest email) {
        service.requestAccessCode(email);
    }

    @ApiOperation(value = "Check access code for invitation")
    @PutMapping(value = "/workspace/codes/check")
    public WorkspaceAccessCodeValidationResult checkAccessCode(@RequestBody WorkspaceAccessCodeValidation accessCode) {
        return service.checkAccessCode(accessCode);
    }


//    @ApiOperation(value = "Validation workspace invite by email")
//    @PutMapping(value = "/workspace/members/check", produces = "application/json")
//    public ResponseEntity checkRegistrationData(@RequestBody WorkspaceRegistration registrationDto) {
//        return service.checkWorkspaceInvite(email);
//    }

}
