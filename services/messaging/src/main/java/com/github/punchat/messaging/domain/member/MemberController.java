package com.github.punchat.messaging.domain.member;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class MemberController {
    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @PutMapping("/channels/{channelId}/members/{userId}")
    public void inviteUser(@PathVariable("channelId") Long channelId, @PathVariable("userId") Long userId) {

    }

    @GetMapping("/channels/{channelId}/members")
    public Set<Member> members(@PathVariable("channelId") Long channelId) {
        return service.findByChannel(channelId);
    }
}
