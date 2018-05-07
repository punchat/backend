package com.github.punchat.am.domain.invite;

import org.springframework.web.bind.annotation.*;

public class InviteController {
    private final InviteService service;

    public InviteController(InviteService service) {
        this.service = service;
    }

    @PatchMapping("/invites/users/{userId}/channels/{channelId}/accept")
    public void accept(@PathVariable("userId") Long recipientUserId,
                       @PathVariable("channelId") Long channelId) {
        service.acceptChannelInvite(recipientUserId, channelId);
    }

    @PatchMapping("/invites/emails/{email}/accept")
    public void accept(@PathVariable String email) {
        service.acceptWorkspaceInvite(email);
    }

    @PostMapping("/invites/users/{userId}/channels/{channelId}")
    public ChannelInvite create(@RequestBody ChannelInvite invite) {
        return service.createChannelInvite(invite);
    }

    @PostMapping("/invites/emails/{email}")
    public WorkspaceInvite create(@RequestBody WorkspaceInvite invite) {
        return service.createWorkspaceInvite(invite);
    }

    @GetMapping("/invites/emails/{email}")
    public Invite get(@PathVariable String email) {
        return service.getInvite(email);
    }

    @GetMapping("/invites/users/{userId}/channels/{channelId}/")
    public Invite get(@PathVariable("userId") Long recipientUserId,
                      @PathVariable("channelId") Long channelId) {
        return service.getInvite(recipientUserId, channelId);
    }
}
