package com.github.punchat.ts.domain.message;

import com.github.punchat.dto.ts.sergey.TopicClassifierRequest;
import com.github.punchat.dto.ts.message.TopicSearchRequest;
import com.github.punchat.ts.messaging.MessagingService;
import com.github.punchat.ts.sergey.SergeyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicMessageServiceImpl implements TopicMessageService {
    private final TopicMessageRepository repository;
    private final SergeyService sergeyService;
    private final MessagingService messagingService;

    public TopicMessageServiceImpl(TopicMessageRepository repository, SergeyService sergeyService, MessagingService messagingService) {
        this.repository = repository;
        this.sergeyService = sergeyService;
        this.messagingService = messagingService;
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

    @Override
    public void saveMessage(Long messageId, Long channelId) {
        TopicMessage newMessage = new TopicMessage();
        newMessage.setMessageId(messageId);
        newMessage.setChannelId(channelId);
        newMessage.setTopics(sergeyService.getTopics(new TopicClassifierRequest(
                messagingService.getText(messageId))));
        repository.save(newMessage);
    }
}
