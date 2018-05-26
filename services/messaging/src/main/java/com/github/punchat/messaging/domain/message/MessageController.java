package com.github.punchat.messaging.domain.message;

import com.github.punchat.dto.messaging.message.BroadcastMessageRequest;
import com.github.punchat.dto.messaging.message.BroadcastMessageResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


@RestController
public class MessageController {
    private final BroadcastMessageFacadeService msgFacade;

    public MessageController(BroadcastMessageFacadeService msgFacade) {
        this.msgFacade = msgFacade;
    }

    @ApiOperation("send message")
    @PostMapping("/messages")
    public BroadcastMessageResponse create(BroadcastMessageRequest request) {
        return msgFacade.create(request);
    }

    @ApiOperation("get channel message by id")
    @GetMapping("/messages/{id}")
    public BroadcastMessageResponse get(@PathVariable("id") Long id) {
        return msgFacade.getById(id);
    }
}
