package com.github.punchat.messaging.domain.invite;

import com.github.punchat.dto.messaging.invite.ChannelInvitationRequest;
import com.github.punchat.dto.messaging.invite.ChannelInvitationResponse;
import com.github.punchat.messaging.domain.role.AbsentPermissionException;
import com.github.punchat.starter.web.error.ApiError;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ChannelInviteController {
    private final ChannelInviteFacadeService service;

    @ApiOperation("invite user to channel")
    @PostMapping("/invitations")
    @ResponseStatus(HttpStatus.CREATED)
    public ChannelInvitationResponse create(@RequestBody ChannelInvitationRequest request) {
        return service.createChannelInvite(request.getChannelId(), request.getRecipientId(), request.getRoleId());
    }

    @ApiOperation("get invitation by id")
    @GetMapping("/invitations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ChannelInvitationResponse getInvitationById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @ApiOperation("accept invitation")
    @PutMapping("/invitations/accepting/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ChannelInvitationResponse accept(@PathVariable("id") Long id) {
        return service.acceptInvitation(id);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AbsentPermissionException.class)
    public ApiError handleException(AbsentPermissionException ex) {
        ApiError error = new ApiError();
        error.setMessage(ex.getMessage());
        return error;
    }
}
