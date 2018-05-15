package com.github.punchat.messaging.domain.channel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RestController
public class ChannelController {
    private final ChannelService service;

    public ChannelController(ChannelService service) {
        this.service = service;
    }

    //getting all channels of user
    @GetMapping("/channels")
    public Set<Channel> getUserChannels(){
        throw new UnsupportedOperationException();
    }

    //creating new channel
    @PostMapping("/channels")
    public BroadcastChannel create(@RequestBody BroadcastChannel channel) {
        return service.createBroadcastChannel(channel);
    }

    //getting particular channel
    @GetMapping("/channels/{id}")
    public Channel get(@PathVariable Long id) {
        return service.get(id);
    }

    //changing channel
    @PutMapping("/channels/{id}")
    public BroadcastChannel update(@PathVariable Long id, @RequestBody BroadcastChannel channel){
        throw new UnsupportedOperationException();
    }

    //deleting channel (checking that channel isn't direct)
    @DeleteMapping("/channels/{id}")
    public void delete(@PathVariable Long id){
        throw new UnsupportedOperationException();
    }
}
