package com.github.punchat.ts.domain.message;

import com.github.punchat.dto.messaging.message.BroadcastMessageResponse;
import com.github.punchat.dto.messaging.message.DirectMessageResponse;
import com.github.punchat.dto.ts.message.TopicDirectSearchRequest;
import com.github.punchat.dto.ts.sergey.TopicClassifierRequest;
import com.github.punchat.dto.ts.message.TopicBroadcastSearchRequest;
import com.github.punchat.ts.messaging.MessagingService;
import com.github.punchat.ts.sergey.SergeyService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class TopicMessageServiceImpl implements TopicMessageService {
    private final TopicBroadcastMessageRepository broadcastRepository;
    private final TopicDirectMessageRepository directRepository;
    private final SergeyService sergeyService;
    private final MessagingService messagingService;

    public TopicMessageServiceImpl(TopicBroadcastMessageRepository broadcastRepository,
                                   TopicDirectMessageRepository directRepository,
                                   SergeyService sergeyService, MessagingService messagingService) {
        this.broadcastRepository = broadcastRepository;
        this.directRepository = directRepository;
        this.sergeyService = sergeyService;
        this.messagingService = messagingService;
    }

    @Override
    public List<TopicMessage> searchBroadcast(TopicBroadcastSearchRequest request) {
        List<Topic> topics = sergeyService.getTopics(new TopicClassifierRequest(request.getText()));
        if (topics.contains(Topic.NOTHING)) return null;
        List<TopicBroadcastMessage> messages = broadcastRepository.findByChannelId(request.getChannelId());
        List<TopicMessage> response = new LinkedList<>();
        sortMessagesByTopics(messages, topics, response);
        return response;
    }

    @Override
    public List<TopicMessage> searchDirect(TopicDirectSearchRequest request) {
        List<Topic> topics = sergeyService.getTopics(new TopicClassifierRequest(request.getText()));
        if (topics.contains(Topic.NOTHING)) return null;
        List<TopicDirectMessage> forwardMessages = directRepository.findBySenderIdAndReceiverId(
                request.getSenderId(), request.getReceiverId());
        List<TopicDirectMessage> backwardMessages = directRepository.findBySenderIdAndReceiverId(
                request.getReceiverId(), request.getSenderId());
        List<TopicMessage> response = new LinkedList<>();
        sortMessagesByTopics(forwardMessages, topics, response);
        sortMessagesByTopics(backwardMessages, topics, response);
        return response;
    }

    private void sortMessagesByTopics(List<? extends TopicMessage> messages, List<Topic> topics,
                                      List<TopicMessage> response) {
        for (TopicMessage message : messages) {
            for (Topic topic : topics) {
                if (message.getTopics().contains(topic)) {
                    response.add(message);
                    break;
                }
            }
        }
    }

    @Override
    public void saveBroadcastMessage(Long messageId, Long channelId) {
        BroadcastMessageResponse message = messagingService.getBroadcastMessage(messageId);
        List<Topic> topics = sergeyService.getTopics(new TopicClassifierRequest(message.getText()));
        broadcastRepository.save(new TopicBroadcastMessage(messageId, topics, channelId));
    }

    @Override
    public void saveDirectMessage(Long messageId) {
        DirectMessageResponse message = messagingService.getDirectMessage(messageId);
        List<Topic> topics = sergeyService.getTopics(new TopicClassifierRequest(message.getText()));
        directRepository.save(new TopicDirectMessage(messageId, topics,
                message.getSender().getId(), message.getReceiver().getId()));
    }
}
