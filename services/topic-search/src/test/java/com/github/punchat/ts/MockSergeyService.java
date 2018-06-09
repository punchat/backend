package com.github.punchat.ts;

import com.github.punchat.dto.ts.sergey.TopicClassifierRequest;
import com.github.punchat.ts.domain.message.Topic;
import com.github.punchat.ts.sergey.SergeyService;

import java.util.ArrayList;
import java.util.List;

public class MockSergeyService implements SergeyService {

    @Override
    public List<Topic> getTopics(TopicClassifierRequest message) {
        List<Topic> response = new ArrayList<>();
        response.add(Topic.ANIMAL);
        response.add(Topic.FOOD);
        return response;
    }
}
