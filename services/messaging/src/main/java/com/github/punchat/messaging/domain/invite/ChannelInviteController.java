package com.github.punchat.messaging.domain.invite;

import com.github.punchat.messaging.domain.role.AbsentPermissionException;
import com.github.punchat.starter.web.error.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class ChannelInviteController {
    private final ChannelInviteService service;

    public ChannelInviteController(ChannelInviteService service) {
        this.service = service;
    }

    @PostMapping("/channel/{channelName}/users/{userId}/roles/{roleId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ChannelInvite create(@PathVariable("channelName") String channelName,
                                @PathVariable("userId") Long userId,
                                @PathVariable("roleId") Long roleId) {
        return service.createChannelInvite(channelName, userId, roleId);
    }

    @GetMapping("/channels/users/{userId}/invited")
    public Set<Long> getUserChannelsInvited(@PathVariable("userId") Long userId) {
        return service.getUserChannelsInvited(userId);
    }

    @PutMapping("/channel/{channelName}/accept")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ChannelInvite accept(@PathVariable("channelName") String channelName) {
        return service.acceptChannelInvite(channelName);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AbsentPermissionException.class)
    public ApiError handleException(AbsentPermissionException ex) {
        ApiError error = new ApiError();
        error.setMessage(ex.getMessage());
        return error;
    }
}
