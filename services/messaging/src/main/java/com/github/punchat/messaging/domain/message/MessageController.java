package com.github.punchat.messaging.domain.message;

import com.github.punchat.dto.messaging.message.MessageDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
public class MessageController {
    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @ApiOperation("get channel message by id")
    @GetMapping("/messages/{id}")
    public Set<MessageDto> get(@PathVariable("id") Long id) {
        throw new UnsupportedOperationException();
    }
}
