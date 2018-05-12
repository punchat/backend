package com.github.punchat.am.domain.invite.channel;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChannelInviteController {
    private final ChannelInviteService service;

    public ChannelInviteController(ChannelInviteService service) {
        this.service = service;
    }

    @PostMapping("/channel/{channelId}/users/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ChannelInvite create(@PathVariable("userId") Long recipientUserId,
                                @PathVariable("channelId") Long channelId) {
        return service.createChannelInvite(recipientUserId, channelId);
    }

    @PutMapping("/channel/{channelId}/accept")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ChannelInvite accept(@PathVariable("channelId") Long channelId) {
        return service.acceptChannelInvite(channelId);
    }
}
