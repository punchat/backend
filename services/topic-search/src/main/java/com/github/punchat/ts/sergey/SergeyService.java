package com.github.punchat.ts.sergey;

import com.github.punchat.dto.ts.sergey.TopicClassifierRequest;
import com.github.punchat.ts.domain.message.Topic;

import java.util.List;

public interface SergeyService {
    List<Topic> getTopics(TopicClassifierRequest message);
}
