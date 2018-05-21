package com.github.punchat.messaging.domain.channel;

import com.github.punchat.dto.messaging.channel.BroadcastChannelRequest;
import com.github.punchat.dto.messaging.channel.BroadcastChannelResponse;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class ChannelController {
    private final BroadcastChannelFinder finder;
    private final ChannelService service;
    private final ChannelMapper mapper;

    public ChannelController(BroadcastChannelFinder finder, ChannelService service, ChannelMapper mapper) {
        this.finder = finder;
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/@me/channels")
    public Set<BroadcastChannelResponse> getAuthorizedUserChannels(){
        return service.getAuthorizedUserChannels()
                .stream().map(mapper::toResponse)
                .collect(Collectors.toSet());
    }

    @GetMapping("/users/{userId}/channels/")
    public Set<BroadcastChannelResponse> getUserChannels(@PathVariable("userId") Long userId){
        return service.getUserChannels(userId)
                .stream().map(mapper::toResponse)
                .collect(Collectors.toSet());
    }


    @PostMapping("/channels")
    public BroadcastChannelResponse create(@RequestBody BroadcastChannelRequest request) {
        return mapper.toResponse(service.create(request));
    }

    @ApiOperation("get channel by long id")
    @GetMapping("/channels/{id}")
    public BroadcastChannelResponse getById(@PathVariable("id") Long channelId) {
        return mapper.toResponse(finder.byId(channelId));
    }

    @ApiOperation("get channel by string name")
    @GetMapping("/channels/{name}")
    public BroadcastChannelResponse getByName(@PathVariable("name") String channelName) {
        return mapper.toResponse(finder.byName(channelName));
    }

    @PutMapping("/channels/{id}")
    public BroadcastChannelResponse updateChannelById(@PathVariable Long id, @RequestBody BroadcastChannelRequest request){
        return mapper.toResponse(service.update(id, request));
    }

    @DeleteMapping("/channels/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
