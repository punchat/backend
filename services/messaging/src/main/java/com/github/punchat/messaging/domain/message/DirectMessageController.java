package com.github.punchat.messaging.domain.message;

import com.github.punchat.dto.messaging.message.DirectMessageRequest;
import com.github.punchat.dto.messaging.message.DirectMessageResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DirectMessageController {
    private final DirectMessageFacadeService drMsgFacade;

    public DirectMessageController(DirectMessageFacadeService drMsgFacade) {
        this.drMsgFacade = drMsgFacade;
    }

    @ApiOperation("send direct message")
    @PostMapping("/messages/direct")
    public DirectMessageResponse sendDirectMessage(@RequestBody DirectMessageRequest request) {
        return drMsgFacade.create(request);
    }

    @ApiOperation("get direct message by id")
    @GetMapping("/messages/direct/{id}")
    public DirectMessageResponse getDirectById(@PathVariable("id") Long id) {
        return drMsgFacade.getById(id);
    }

    @ApiOperation("get last direct messages between two users")
    @GetMapping("/messages/direct/between/{user1Id}/{user2Id}/last")
    public List<DirectMessageResponse> getLastMessages(@PathVariable("user1Id") Long user1Id,
                                                       @PathVariable("user2Id") Long user2Id,
                                                       @RequestParam(value = "limit", defaultValue = "10", required = false) Integer limit) {
        return drMsgFacade.getLast(user1Id, user2Id, limit);
    }

    @ApiOperation("get direct messages between two users before specified message")
    @GetMapping("/messages/direct/between/{user1Id}/{user2Id}/before")
    public List<DirectMessageResponse> getMessagesBeforeSpecifiedId(@PathVariable("user1Id") Long user1Id,
                                                                    @PathVariable("user2Id") Long user2Id,
                                                                    @RequestParam("id") Long id,
                                                                    @RequestParam(value = "limit", defaultValue = "10", required = false) Integer limit) {
        return drMsgFacade.getBefore(user1Id, user2Id, id, limit);
    }

    @ApiOperation("get direct messages between two users after specified message")
    @GetMapping("/messages/direct/between/{user1Id}/{user2Id}/after")
    public List<DirectMessageResponse> getMessagesAfterSpecifiedId(@PathVariable("user1Id") Long user1Id,
                                                                      @PathVariable("user2Id") Long user2Id,
                                                                      @RequestParam("id") Long id,
                                                                      @RequestParam(value = "limit", defaultValue = "10", required = false) Integer limit) {
        return drMsgFacade.getAfter(user1Id, user2Id, id, limit);
    }
}
