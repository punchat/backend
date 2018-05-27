package com.github.punchat.messaging.domain.message;

import com.github.punchat.dto.messaging.message.BroadcastMessageRequest;
import com.github.punchat.dto.messaging.message.BroadcastMessageResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BroadcastMessageController {
    private final BroadcastMessageFacadeService bcMsgFacade;

    public BroadcastMessageController(BroadcastMessageFacadeService bcMsgFacade) {
        this.bcMsgFacade = bcMsgFacade;
    }

    @ApiOperation("send message to channel")
    @PostMapping("/messages/broadcast")
    public BroadcastMessageResponse sendBroadcastMessage(BroadcastMessageRequest request) {
        return bcMsgFacade.create(request);
    }

    @ApiOperation("get channel message by id")
    @GetMapping("/messages/broadcast/{id}")
    public BroadcastMessageResponse getBroadcastMessageById(@PathVariable("id") Long id) {
        return bcMsgFacade.getById(id);
    }

    @ApiOperation("get last messages")
    @GetMapping("/messages/broadcast/last")
    public List<BroadcastMessageResponse> getLastMessages(@PathVariable("id") Long id,
                                                          @RequestParam(value = "limit", defaultValue = "10", required = false) Integer limit) {
        return bcMsgFacade.getLast(id, limit);
    }

    @ApiOperation("get messages before specified message")
    @GetMapping("/messages/broadcast/before")
    public List<BroadcastMessageResponse> getMessagesBeforeSpecifiedId(@PathVariable("channelId") Long id,
                                                                       @RequestParam(value = "id") Long anchor,
                                                                       @RequestParam(value = "limit", defaultValue = "10", required = false) Integer limit) {
        return bcMsgFacade.getBefore(id, anchor, limit);
    }

    @ApiOperation("get messages after specified message")
    @GetMapping("/messages/broadcast/after")
    public List<BroadcastMessageResponse> getMessagesAfterSpecifiedId(@PathVariable("channelId") Long id,
                                                                      @RequestParam(value = "id") Long anchor,
                                                                      @RequestParam(value = "limit", defaultValue = "10", required = false) Integer limit) {
        return bcMsgFacade.getAfter(id, anchor, limit);
    }
}
