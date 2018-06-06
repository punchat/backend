package com.github.punchat.ts.domain.message;

import com.github.punchat.dto.ts.message.TopicBroadcastSearchRequest;
import com.github.punchat.dto.ts.message.TopicDirectSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicMessageController {
    private final TopicMessageService service;

    @Autowired
    public TopicMessageController(TopicMessageService service) {
        this.service = service;
    }

    @PostMapping("/topicSearch/broadcast")
    public @ResponseBody List<TopicMessage> search(@RequestBody TopicBroadcastSearchRequest request){
        return service.searchBroadcast(request);
    }

    @PostMapping("/topicSearch/direct")
    public @ResponseBody List<TopicMessage> search(@RequestBody TopicDirectSearchRequest request){
        return service.searchDirect(request);
    }

}
