package com.github.punchat.messaging.domain.invite;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChannelInviteController {
    private final ChannelInviteService service;

    public ChannelInviteController(ChannelInviteService service) {
        this.service = service;
    }

    @PostMapping("/channel/{channel}/users/{username}")
    @ResponseStatus(HttpStatus.CREATED)
    public ChannelInvite create(@PathVariable("channel") String channel,
                                @PathVariable("username") String username) {
        return service.createChannelInvite(channel, username);
    }

    @PutMapping("/channel/{channel}/accept")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ChannelInvite accept(@PathVariable("channel") String channel) {
        return service.acceptChannelInvite(channel);
    }
}
