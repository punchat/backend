package com.github.punchat.messaging.domain.member;

import com.github.punchat.dto.messaging.member.MemberDto;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class MemberController {
    private final MemberFacadeService service;
    private final MemberMapper mapper;

    @ApiOperation("get all members of channel")
    @GetMapping("/channels/{channelId}/members")
    public Set<MemberDto> members(@PathVariable("channelId") Long channelId) {
        return service.getMembers(channelId)
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toSet());
    }

    @ApiOperation(value = "exclude user from channel")
    @DeleteMapping("/channels/{channelId}/members/{userId}")
    public void exclude(@PathVariable Long channelId, @PathVariable Long userId) {
        service.delete(userId, channelId);
    }
}
