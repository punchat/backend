package com.github.punchat.messaging.domain.user;

import com.github.punchat.dto.messaging.channel.BroadcastChannelResponse;
import com.github.punchat.dto.messaging.invite.ChannelInvitationResponse;
import com.github.punchat.messaging.domain.channel.ChannelMapper;
import com.github.punchat.messaging.domain.channel.ChannelService;
import com.github.punchat.messaging.domain.invite.ChannelInviteFacadeService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class UserController {
    private final ChannelService service;
    private final ChannelMapper channelMapper;

    private final ChannelInviteFacadeService invitations;

    @ApiOperation("get all channels of current user")
    @GetMapping("/users/@me/channels")
    public List<BroadcastChannelResponse> getCurrentUserChannels() {
        return service.getAuthorizedUserChannels()
                .stream().map(channelMapper::toResponse)
                .collect(Collectors.toList());
    }

    @ApiOperation("get all invitations for current user")
    @GetMapping("/users/@me/invitations")
    public List<ChannelInvitationResponse> getCurrentUserInvitations() {
        return invitations.getAuthorizedUserInvites();
    }
}
