package com.github.punchat.messaging.domain.message;

import com.github.punchat.dto.messaging.message.BroadcastMessageRequest;
import com.github.punchat.dto.messaging.message.BroadcastMessageResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


@RestController
public class BroadcastMessageController {
    private final BroadcastMessageFacadeService bcMsgFacade;

    public BroadcastMessageController(BroadcastMessageFacadeService bcMsgFacade) {
        this.bcMsgFacade = bcMsgFacade;
    }

    @ApiOperation("send message to channel")
    @PostMapping("/messages/broadcast")
    public BroadcastMessageResponse sendBroadcastMessage(@RequestBody BroadcastMessageRequest request) {
        return bcMsgFacade.create(request);
    }

    @ApiOperation("get channel message by id")
    @GetMapping("/messages/broadcast/{id}")
    public BroadcastMessageResponse getBroadcastMessageById(@PathVariable("id") Long id) {
        return bcMsgFacade.getById(id);
    }
}
