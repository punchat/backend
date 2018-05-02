package com.github.punchat.am.domain.invite;

import org.springframework.web.bind.annotation.*;

public class InviteController {
    private final InviteService service;

    public InviteController(InviteService service) {

        this.service = service;
    }

    @GetMapping("/invites/{userId}/{channelId}/")
    public Invite get(@PathVariable("userId") Long recipientUserId,
                      @PathVariable("channelId") Long channelId) {
        return service.getInvite(recipientUserId, channelId);
    }

    @GetMapping("/invites/{email}")
    public Invite get(@PathVariable String email) {

        return service.getInvite(email);
    }

    @PostMapping("/invites/{userId}/{channelId}")
    public ChannelInvite create(@RequestBody ChannelInvite invite) {

        return service.createChannelInvite(invite);
    }

    @PostMapping("/invites/{email}")
    public WorkspaceInvite create(@RequestBody WorkspaceInvite invite) {

        return service.createWorkspaceInvite(invite);
    }
}
