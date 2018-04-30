package com.github.punchat.messaging.domain.channel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ChannelController {
    private final ChannelService service;

    public ChannelController(ChannelService service) {
        this.service = service;
    }

    @PostMapping("/channels")
    public BroadcastChannel create(@RequestBody BroadcastChannel channel) {
        return service.createBroadcastChannel(channel);
    }

    @GetMapping("/channels/{id}")
    public BroadcastChannel get(@PathVariable Long id) {
        return service.get(id);
    }
}
