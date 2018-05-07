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

    @PostMapping("/invites/{email}/code")
    public boolean checkAccessCode(@PathVariable String email,
                                   @RequestBody String code) {
        return service.checkAccessCode(email, code);
    }

    @GetMapping("/invites/emails/{email}/state")
    public String getState(@PathVariable String email) {
        return service.getEmailState(email);
    }
}
