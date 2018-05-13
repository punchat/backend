package com.github.punchat.messaging;

import com.github.punchat.messaging.domain.role.RoleController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = MessagingApplicationTestsConfig.class)
@SpringBootTest
public class MessagingApplicationTests {
//    @Autowired
//    MockMvc mockMvc;
//    @MockBean
//    private ChannelService channelService;
//    @MockBean
//    private MemberService memberService;
//    @MockBean
//    private RoleService roleService;
//    @MockBean
//    private MessageService messageService;
    @Autowired
    private RoleController roleController;
    @Test
    public void contextLoads() throws Exception {
        assertThat(roleController).isNotNull();
    }
}
