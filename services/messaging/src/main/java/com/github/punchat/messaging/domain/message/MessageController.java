package com.github.punchat.messaging.domain.message;

import com.github.punchat.dto.messaging.message.MessageDto;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
public class MessageController {
    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    //writing new message to some channel (permission check)
    @PostMapping("/channels/{id}/messages")
    public MessageDto create(@PathVariable Long id, @RequestBody Message message) {
        throw new UnsupportedOperationException();
    }

    //getting all messages of some channel
    @GetMapping("/channels/{id}/messages")
    public Set<MessageDto> get(@PathVariable Long id) {

        throw new UnsupportedOperationException();
    }
}
