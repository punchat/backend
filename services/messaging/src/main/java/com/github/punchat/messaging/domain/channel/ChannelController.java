package com.github.punchat.messaging.domain.channel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ChannelController {
    private final ChannelService service;

    public ChannelController(ChannelService service) {
        this.service = service;
    }

    @GetMapping("/users/{userId}/channels/direct")
    public DirectChannel getUserChannel(@PathVariable Long userId) {
        DirectChannel channel = service.getDirectChannel(userId);
        log.info(channel.toString());
        return channel;
    }
}
