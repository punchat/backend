package com.github.punchat.messaging.domain.message;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }
}
