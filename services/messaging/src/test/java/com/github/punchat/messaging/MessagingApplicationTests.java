package com.github.punchat.messaging;

import com.github.punchat.messaging.domain.invite.ChannelInviteRepository;
import com.github.punchat.messaging.domain.role.RoleController;
import com.github.punchat.messaging.domain.role.RoleService;
import com.github.punchat.messaging.events.EventBus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles({"test", "unsecured"})
@SpringBootTest(classes = ComponentTestsConfiguration.class)
public class MessagingApplicationTests {
    @Autowired
    private RoleController roleController;

    @Autowired
    private EventBus eventBus;

    @Autowired
    private ChannelInviteRepository channelInviteRepository;

    @Autowired
    private RoleService roleService;
    @Test
    public void contextLoads() throws Exception {
        assertThat(roleController).isNotNull();
        assertThat(channelInviteRepository).isNotNull();
        assertThat(eventBus).isNotNull();
        assertThat(roleService).isNotNull();
    }
}
