package com.github.punchat.messaging.domain.member;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
public class MemberController {
    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    //inviting user to channel (user can refuse invitation, here is only permission check)
    @PutMapping("/channels/{channelId}/members")
    public void inviteUser(@PathVariable("channelId") Long channelId, @Valid @RequestBody Invitation invitation) {
        throw new UnsupportedOperationException();
    }

    //getting members of channel
    @GetMapping("/channels/{channelId}/members")
    public Set<Member> members(@PathVariable("channelId") Long channelId) {
        return service.findByChannel(channelId);
    }

    //excluding member of channel (permission check)
    @DeleteMapping("/channels/{channelId}/members/{memberId}")
    public void exclude(@PathVariable Long channelId, @PathVariable Long memberId) {
        throw new UnsupportedOperationException();
    }
}
