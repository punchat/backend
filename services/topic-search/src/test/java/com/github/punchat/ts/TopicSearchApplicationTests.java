package com.github.punchat.ts;

import com.github.punchat.dto.ts.message.TopicBroadcastSearchRequest;
import com.github.punchat.dto.ts.message.TopicDirectSearchRequest;
import com.github.punchat.events.NewBroadcastMessageEvent;
import com.github.punchat.events.NewDirectMessageEvent;
import com.github.punchat.ts.domain.message.*;
import com.github.punchat.ts.events.Channels;
import com.github.punchat.ts.events.Events;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = ComponentTestsConfiguration.class)
public class TopicSearchApplicationTests {

    @Autowired
    private TopicMessageController controller;

    @Autowired
    private TopicBroadcastMessageRepository broadcastRepository;

    @Autowired
    private TopicDirectMessageRepository directRepository;

    @Autowired
    private Channels channels;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
        assertThat(broadcastRepository).isNotNull();
        assertThat(directRepository).isNotNull();
        assertThat(channels).isNotNull();
    }

    @Test
    @Transactional
    public void handlingNewBroadcastMessageEvent() {
        NewBroadcastMessageEvent newMessage = new NewBroadcastMessageEvent(1L, 1L);
        channels.newBroadcastMessageEvents().send(MessageBuilder.withPayload(newMessage).build());

        TopicBroadcastMessage savedMessage = broadcastRepository.getOne(1L);

        assertThat(savedMessage.getChannelId()).isEqualTo(1L);
        assertThat(savedMessage.getTopics().contains(Topic.animal)).isEqualTo(true);
        assertThat(savedMessage.getTopics().contains(Topic.food)).isEqualTo(true);
    }

    @Test
    @Transactional
    public void handlingNewDirectMessageEvent() {
        NewDirectMessageEvent newMessage = new NewDirectMessageEvent(2L);
        channels.newDirectMessageEvents().send(MessageBuilder.withPayload(newMessage).build());

        TopicDirectMessage savedMessage = directRepository.getOne(2L);

        assertThat(savedMessage.getSenderId()).isEqualTo(1L);
        assertThat(savedMessage.getReceiverId()).isEqualTo(2L);
        assertThat(savedMessage.getTopics().contains(Topic.animal)).isEqualTo(true);
        assertThat(savedMessage.getTopics().contains(Topic.food)).isEqualTo(true);
    }

    @Test
    @Transactional
    public void broadcastMessageSearch(){
        //filling with data
        List<Topic> food = new LinkedList<>();
        food.add(Topic.food);
        List<Topic> family = new LinkedList<>();
        family.add(Topic.family);

        broadcastRepository.save(new TopicBroadcastMessage(1L, food, 1L)); //correct
        broadcastRepository.save(new TopicBroadcastMessage(2L, family, 1L)); //wrong topic
        broadcastRepository.save(new TopicBroadcastMessage(3L, food, 2L)); //wrong channel

        //search test
        List<TopicMessage> result = controller.search(new TopicBroadcastSearchRequest(
                1L, "Hi"));
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getMessageId()).isEqualTo(1L);
    }

    @Test
    @Transactional
    public void directMessageSearch(){
        //filling with data
        List<Topic> food = new LinkedList<>();
        food.add(Topic.food);
        List<Topic> family = new LinkedList<>();
        family.add(Topic.family);

        directRepository.save(new TopicDirectMessage(1L, food, 1L, 2L)); //correct
        directRepository.save(new TopicDirectMessage(2L, food, 2L, 1L)); //also correct
        directRepository.save(new TopicDirectMessage(3L, food, 1L, 3L)); //wrong receiver
        directRepository.save(new TopicDirectMessage(4L, food, 3L, 2L)); //wrong sender
        directRepository.save(new TopicDirectMessage(5L, family, 1L, 2L)); //wrong topic

        //search test
        List<TopicMessage> result = controller.search(new TopicDirectSearchRequest(
                1L, 2L, "Hello"));
        assertThat(result.size()).isEqualTo(2);
        for (TopicMessage message : result)
            assertThat(message.getMessageId()).isStrictlyBetween(0L, 3L);
    }
}
