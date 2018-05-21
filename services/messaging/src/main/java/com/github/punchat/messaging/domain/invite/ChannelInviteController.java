package com.github.punchat.messaging.domain.invite;

import com.github.punchat.dto.messaging.invite.ChannelInviteDto;
import com.github.punchat.dto.messaging.member.AddNewMembersDto;
import com.github.punchat.messaging.domain.role.AbsentPermissionException;
import com.github.punchat.starter.web.error.ApiError;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class ChannelInviteController {
    private final ChannelInviteService service;

    public ChannelInviteController(ChannelInviteService service) {
        this.service = service;
    }

    @ApiOperation("invite user to channel")
    @PostMapping("/channels/{channelId}/members")
    @ResponseStatus(HttpStatus.CREATED)
    public ChannelInviteDto create(@PathVariable("channelId") Long channelId,
                                @RequestBody AddNewMembersDto payload) {
        throw new UnsupportedOperationException();
    }

    @ApiOperation("get all invitations for current user")
    @GetMapping("/@me/invitations")
    @ResponseStatus(HttpStatus.OK)
    public Set<ChannelInviteDto> getInvitations() {
        throw new UnsupportedOperationException();
    }

    @ApiOperation("accept invitation")
    @PutMapping("/@me/invitations/{invitationId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ChannelInvite accept(@PathVariable("invitationId") Long invitationId) {
        throw new UnsupportedOperationException();
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AbsentPermissionException.class)
    public ApiError handleException(AbsentPermissionException ex) {
        ApiError error = new ApiError();
        error.setMessage(ex.getMessage());
        return error;
    }
}
