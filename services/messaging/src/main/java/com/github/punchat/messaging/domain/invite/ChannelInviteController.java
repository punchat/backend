package com.github.punchat.messaging.domain.invite;

import com.github.punchat.dto.messaging.invite.ChannelInvitationRequest;
import com.github.punchat.dto.messaging.invite.ChannelInvitationResponse;
import com.github.punchat.messaging.domain.role.AbsentPermissionException;
import com.github.punchat.starter.web.error.ApiError;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class ChannelInviteController {
    private final ChannelInviteFacadeServiceImpl service;
    private final ChannelInviteMapper mapper;

    @ApiOperation("invite user to channel")
    @PostMapping("/channels/{channelId}/members")
    @ResponseStatus(HttpStatus.CREATED)
    public ChannelInvitationResponse create(@PathVariable("channelId") Long channelId,
                                            @RequestBody ChannelInvitationRequest request) {
        return mapper.toResponse(service.createChannelInvitation(channelId, request.getRecipientId(), request.getRoleId()));
    }

    @ApiOperation("get all invitations for current user")
    @GetMapping("/@me/invitations")
    @ResponseStatus(HttpStatus.OK)
    public Set<ChannelInvitationResponse> getInvitations() {
        return service.getAuthorizedUserInvitations().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toSet());
    }

    @ApiOperation("accept invitation")
    @PutMapping("/@me/invitations/{channelId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ChannelInvitationResponse accept(@PathVariable("channelId") Long channelId) {
        return mapper.toResponse(service.acceptChannelInvitation(channelId));
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AbsentPermissionException.class)
    public ApiError handleException(AbsentPermissionException ex) {
        ApiError error = new ApiError();
        error.setMessage(ex.getMessage());
        return error;
    }
}
