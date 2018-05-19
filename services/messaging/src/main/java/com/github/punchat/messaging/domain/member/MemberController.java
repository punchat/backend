package com.github.punchat.messaging.domain.member;

import com.github.punchat.dto.messaging.member.InvitationDto;
import com.github.punchat.dto.messaging.member.MemberDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
public class MemberController {
    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    //inviting user to channel (user can refuse invitationDto, here is only permission check)
    @PutMapping("/channels/{channelId}/members")
    public void inviteUser(@PathVariable("channelId") Long channelId, @Valid @RequestBody InvitationDto invitationDto) {
        throw new UnsupportedOperationException();
    }

    //getting members of channel
    @GetMapping("/channels/{channelId}/members")
    public Set<MemberDto> members(@PathVariable("channelId") Long channelId) {
        throw new UnsupportedOperationException();
        //return service.findByChannel(channelId);
    }

    //excluding member of channel (permission check)
    @DeleteMapping("/channels/{channelId}/members/{memberId}")
    public void exclude(@PathVariable Long channelId, @PathVariable Long memberId) {
        throw new UnsupportedOperationException();
    }
}
