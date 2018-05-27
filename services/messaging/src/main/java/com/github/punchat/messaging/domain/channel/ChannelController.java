package com.github.punchat.messaging.domain.channel;

import com.github.punchat.dto.messaging.channel.BroadcastChannelRequest;
import com.github.punchat.dto.messaging.channel.BroadcastChannelResponse;
import com.github.punchat.dto.messaging.invite.ChannelInvitationResponse;
import com.github.punchat.dto.messaging.member.MemberDto;
import com.github.punchat.dto.messaging.message.BroadcastMessageResponse;
import com.github.punchat.dto.messaging.role.RoleDto;
import com.github.punchat.messaging.domain.invite.ChannelInviteFacadeService;
import com.github.punchat.messaging.domain.member.MemberFacadeService;
import com.github.punchat.messaging.domain.message.BroadcastMessageFacadeService;
import com.github.punchat.messaging.domain.role.RoleFacadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Api("Channels operations")
@Slf4j
@RestController
@AllArgsConstructor
public class ChannelController {
    private final BroadcastChannelFinder finder;
    private final ChannelService service;
    private final ChannelMapper mapper;

    private final MemberFacadeService membersFacade;
    private final ChannelInviteFacadeService invitesFacade;
    private final RoleFacadeService rolesFacade;
    private final BroadcastMessageFacadeService messagesService;

    @ApiOperation("create new channel")
    @PostMapping("/channels")
    public BroadcastChannelResponse createNewChannel(@RequestBody BroadcastChannelRequest request) {
        return mapper.toResponse(service.create(request));
    }

    @ApiOperation("get all public channels")
    @GetMapping("/channels")
    public Set<BroadcastChannelResponse> getAllPublicChannels() {
        return service.getAllPublicChannels().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toSet());
    }

    @ApiOperation("get channel info by id")
    @GetMapping("/channels/{id}")
    public BroadcastChannelResponse getChannelInfoById(@PathVariable("id") Long id) {
        return mapper.toResponse(finder.byId(id));
    }

    @ApiOperation("join to public channel")
    @PostMapping("/channels/{id}/joining")
    public MemberDto joinChannelById(@PathVariable("id") Long id) {
        return membersFacade.join(id);
    }

    @ApiOperation("update channel info by id")
    @PutMapping("/channels/{id}")
    public BroadcastChannelResponse updateChannelInfo(@PathVariable("id") Long id, @RequestBody BroadcastChannelRequest request) {
        return mapper.toResponse(service.update(id, request));
    }

    @ApiOperation("delete channel")
    @DeleteMapping("/channels/{id}")
    public void deleteChannel(@PathVariable("id") Long id) {
        service.delete(id);
    }

    @ApiOperation("get all members of the channel")
    @GetMapping("/channels/{id}/members")
    public Set<MemberDto> getAllMembers(@PathVariable("id") Long id) {
        return membersFacade.getMembers(id);
    }

    @ApiOperation("get current user as a member of the channel")
    @GetMapping("/channels/{id}/members/@me")
    public MemberDto getCurrentUserAsMember(@PathVariable("id") Long id) {
        return membersFacade.getAuthorizedUserAsChannelMembers(id);
    }

    @ApiOperation("get invitations list of the channel")
    @GetMapping("/channels/{id}/invitations")
    public Set<ChannelInvitationResponse> getAllInvitations(@PathVariable("id") Long id) {
        return invitesFacade.getChannelInvites(id);
    }

    @ApiOperation("get available roles of the channel")
    @GetMapping("/channels/{id}/roles")
    public Set<RoleDto> getChannelRoles(@PathVariable("id") Long id) {
        return rolesFacade.getChannelRoles(id);
    }

    @ApiOperation("get last messages")
    @GetMapping("/channels/{id}/messages/last")
    public List<BroadcastMessageResponse> getLastMessages(@PathVariable("id") Long id,
                                                          @RequestParam(value = "limit", defaultValue = "10", required = false) Integer limit) {
        return messagesService.getLast(id, limit);
    }

    @ApiOperation("get messages before specified message")
    @GetMapping("/channels/{channelId}/messages/before")
    public List<BroadcastMessageResponse> getMessagesBeforeSpecifiedId(@PathVariable("channelId") Long id,
                                                                       @RequestParam(value = "id") Long anchor,
                                                                       @RequestParam(value = "limit", defaultValue = "10", required = false) Integer limit) {
        return messagesService.getBefore(id, anchor, limit);
    }

    @ApiOperation("get messages after specified message")
    @GetMapping("/channels/{channelId}/messages/after")
    public List<BroadcastMessageResponse> getMessagesAfterSpecifiedId(@PathVariable("channelId") Long id,
                                                                      @RequestParam(value = "id") Long anchor,
                                                                      @RequestParam(value = "limit", defaultValue = "10", required = false) Integer limit) {
        return messagesService.getAfter(id, anchor, limit);
    }
}
