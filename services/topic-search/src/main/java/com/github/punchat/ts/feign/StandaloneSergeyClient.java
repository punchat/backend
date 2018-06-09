package com.github.punchat.ts.feign;

import com.github.punchat.dto.ts.sergey.TopicClassifierRequest;
import com.github.punchat.dto.ts.sergey.TopicClassifierResponse;
import com.github.punchat.ts.domain.message.Topic;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "Sergey", url = "http://localhost:7777")
public interface StandaloneSergeyClient {

    @PostMapping("/TopicClassifier")
    TopicClassifierResponse getTopics(@RequestBody TopicClassifierRequest message);
}
