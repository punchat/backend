package com.github.punchat.messaging.domain.channel;

import com.github.punchat.dto.messaging.channel.BroadcastChannelDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class ChannelController {
    private final ChannelService service;
    private final ChannelMapper mapper;

    public ChannelController(ChannelService service, ChannelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/@me/channels")
    public Set<BroadcastChannelDto> getAuthorizedUserChannels(){
        return service.getAuthorizedUserChannels()
                .stream().map(mapper::channelToChannelDto)
                .collect(Collectors.toSet());
    }

    @GetMapping("/users/{userId}/channels/")
    public Set<BroadcastChannelDto> getUserChannels(@PathVariable("userId") Long userId){
        return service.getUserChannels(userId)
                .stream().map(mapper::channelToChannelDto)
                .collect(Collectors.toSet());
    }

    @PostMapping("/channels")
    public BroadcastChannelDto create(@RequestBody BroadcastChannelDto channelDto) {
        BroadcastChannel channel = mapper.channelDtoToChannel(channelDto);
        return mapper.channelToChannelDto(service.createBroadcastChannel(channel));
    }

    @GetMapping("/channels/{id}")
    public BroadcastChannelDto get(@PathVariable Long id) {
        return mapper.channelToChannelDto(service.get(id));
    }

    @PutMapping("/channels/{id}")
    public BroadcastChannelDto update(@PathVariable Long id, @RequestBody BroadcastChannelDto channel){
        throw new UnsupportedOperationException();
    }

    @DeleteMapping("/channels/{id}")
    public void delete(@PathVariable Long id){
        throw new UnsupportedOperationException();
    }
}
