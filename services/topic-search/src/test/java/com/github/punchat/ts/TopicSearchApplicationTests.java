package com.github.punchat.ts;

import com.github.punchat.dto.ts.message.TopicSearchRequest;
import com.github.punchat.events.NewBroadcastMessageEvent;
import com.github.punchat.ts.domain.message.Topic;
import com.github.punchat.ts.domain.message.TopicMessage;
import com.github.punchat.ts.domain.message.TopicMessageController;
import com.github.punchat.ts.domain.message.TopicMessageRepository;
import com.github.punchat.ts.events.Channels;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = ComponentTestsConfiguration.class)
public class TopicSearchApplicationTests {

    @Autowired
    private TopicMessageController controller;

    @Autowired
    private TopicMessageRepository repository;

    @Autowired
    private Channels channels;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
        assertThat(repository).isNotNull();
        assertThat(channels).isNotNull();
    }

    @Test
    public void controllerSearch() {
        //filling with data
        TopicMessage[] messages = new TopicMessage[3];
        messages[0] = new TopicMessage();
        messages[1] = new TopicMessage();
        messages[2] = new TopicMessage();
        messages[0].setChannelId(1L);
        messages[1].setChannelId(1L);
        messages[2].setChannelId(2L);
        List<Topic> animal = new ArrayList<>();
        animal.add(Topic.animal);
        List<Topic> family = new ArrayList<>();
        family.add(Topic.family);
        List<Topic> food = new ArrayList<>();
        food.add(Topic.food);
        messages[0].setTopics(animal);
        messages[1].setTopics(family);
        messages[2].setTopics(food);
        for (int i = 0; i < 3; i++){
            messages[i].setMessageId((long)i);
            repository.save(messages[i]);
        }

        //controller test
        TopicSearchRequest request = new TopicSearchRequest();
        request.setText("animal food");
        request.setChannelId(1L);
        List<TopicMessage> response = controller.search(request);
        for (TopicMessage message : response) {
            assertThat(message.getMessageId()).isEqualTo(0L);
        }
    }

    @Test
    @Transactional
    public void handlingNewMessageEvent() {
        NewBroadcastMessageEvent newMessage = new NewBroadcastMessageEvent();
        newMessage.setMessageId(3L);
        newMessage.setChannelId(3L);
        channels.newBroadcastMessageEvents().send(MessageBuilder.withPayload(newMessage).build());

        TopicMessage savedMessage = repository.getOne(3L);

        assertThat(savedMessage.getChannelId()).isEqualTo(3L);
        assertThat(savedMessage.getTopics().contains(Topic.animal)).isEqualTo(true);
        assertThat(savedMessage.getTopics().contains(Topic.food)).isEqualTo(true);
    }
}
