package com.github.punchat.ts.sergey;

import com.github.punchat.dto.ts.sergey.TopicClassifierRequest;
import com.github.punchat.dto.ts.sergey.TopicClassifierResponse;
import com.github.punchat.ts.domain.message.Topic;
import com.github.punchat.ts.feign.StandaloneSergeyClient;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("!test")
public class StandaloneSergeyClientAdapter implements SergeyService {
    private final StandaloneSergeyClient sergeyClient;

    public StandaloneSergeyClientAdapter(StandaloneSergeyClient sergeyClient) {
        this.sergeyClient = sergeyClient;
    }

    @Override
    public List<Topic> getTopics(TopicClassifierRequest message) {
        TopicClassifierResponse sergeyResponse = sergeyClient.getTopics(message);
        List<Topic> topics = new ArrayList<>();
        for (String topic : sergeyResponse.getTopic()) {
            topics.add(Topic.valueOf(topic));
        }
        return topics;
    }
}