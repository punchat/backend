package com.github.punchat.messaging.domain.member;

import com.github.punchat.dto.messaging.member.MemberDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class MemberController {
    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @ApiOperation("get all members of channel")
    @GetMapping("/channels/{channelId}/members")
    public Set<MemberDto> members(@PathVariable("channelId") Long channelId) {
        throw new UnsupportedOperationException();
    }

    @ApiOperation(value = "exclude user from channel")
    @DeleteMapping("/channels/{channelId}/members/{userId}")
    public void exclude(@PathVariable Long channelId, @PathVariable Long userId) {
        throw new UnsupportedOperationException();
    }
}
