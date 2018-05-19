package com.github.punchat.messaging.domain.invite;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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

    @GetMapping("/channels/users/{userId}/invited")
    public Set<Long> getUserChannelsInvited(@PathVariable("userId") Long userId){
        return service.getUserChannelsInvited(userId);
    }

    @PutMapping("/channel/{channelId}/accept")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ChannelInvite accept(@PathVariable("channelId") Long channelId) {
        return service.acceptChannelInvite(channelId);
    }
}
