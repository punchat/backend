package com.github.punchat.ts.domain.message;

import com.github.punchat.dto.ts.sergey.TopicClassifierRequest;
import com.github.punchat.dto.ts.message.TopicSearchRequest;
import com.github.punchat.ts.sergey.SergeyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicMessageServiceImpl implements TopicMessageService {
    private TopicMessageRepository repository;
    private SergeyService sergeyService;

    public TopicMessageServiceImpl(TopicMessageRepository repository, SergeyService sergeyService) {
        this.repository = repository;
        this.sergeyService = sergeyService;
    }

    @Override
    public List<TopicMessage> search(TopicSearchRequest request) {
        List<Topic> topics = sergeyService.getTopics(new TopicClassifierRequest(request.getText()));
        if (topics.contains(Topic.nothing)) return null;
        List<TopicMessage> messages = repository.findByChannelId(request.getChannelId());
        List<TopicMessage> response = new ArrayList<>();
        for (TopicMessage message : messages) {
            for (Topic topic : topics) {
                if (message.getTopics().contains(topic)) {
                    response.add(message);
                    break;
                }
            }
        }
        return response;
    }
}
