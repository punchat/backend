package com.github.punchat.messaging.domain.channel;

import com.github.punchat.dto.messaging.channel.BroadcastChannelRequest;
import com.github.punchat.messaging.domain.user.User;
import com.github.punchat.messaging.domain.user.UserService;
import com.github.punchat.messaging.id.IdService;
import com.github.punchat.starter.uaa.client.context.AuthContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@ContextConfiguration(classes = JpaTestsConfig.class)
@RunWith(SpringRunner.class)
public class BroadcastChannelRepositoryTest {
    @Autowired
    private BroadcastChannelRepository repo;

    @Autowired
    private UserService userService;

    @Autowired
    private IdService idService;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private AuthContext authContext;

    @Test
    public void whenUserCreatingChannel_thenHeBecomesMember() {
        User user1 = userService.createUser(idService.next());
        SecurityUtils.withUser(authContext, user1);
        for (int channelNum = 0; channelNum < 3; ++channelNum) {
            BroadcastChannelRequest channel = new BroadcastChannelRequest();
            channel.setName("channel1#" + channelNum);
            channelService.create(channel);
        }
        User user2 = userService.createUser(idService.next());
        SecurityUtils.withUser(authContext, user2);
        for (int channelNum = 0; channelNum < 7; ++channelNum) {
            BroadcastChannelRequest channel = new BroadcastChannelRequest();
            channel.setName("channel2#" + channelNum);
            channelService.create(channel);
        }

        assertThat(repo.findUserChannels(user1).size()).isEqualTo(3);
        assertThat(repo.findUserChannels(user2).size()).isEqualTo(7);
    }
}