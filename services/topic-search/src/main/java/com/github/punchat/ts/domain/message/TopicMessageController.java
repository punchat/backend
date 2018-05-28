package com.github.punchat.ts.domain.message;

import com.github.punchat.dto.ts.message.TopicSearchRequest;
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

    @PostMapping("/topicSearch")
    public @ResponseBody List<TopicMessage> search(@RequestBody TopicSearchRequest request){
        return service.search(request);
    }

}
