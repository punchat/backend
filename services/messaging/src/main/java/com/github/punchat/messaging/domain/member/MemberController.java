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

    @PutMapping("/channels/{channelId}/members")
    public void inviteUser(@PathVariable("channelId") Long channelId, @Valid @RequestBody Invitation invitation) {

    }

    @GetMapping("/channels/{channelId}/members")
    public Set<Member> members(@PathVariable("channelId") Long channelId) {
        return service.findByChannel(channelId);
    }
}
