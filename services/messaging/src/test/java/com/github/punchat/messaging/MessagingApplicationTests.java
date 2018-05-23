package com.github.punchat.messaging;

import com.github.punchat.messaging.domain.role.RoleController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = ComponentTestsConfiguration.class)
public class MessagingApplicationTests {
    @Autowired
    private RoleController roleController;
    @Test
    public void contextLoads() throws Exception {
        assertThat(roleController).isNotNull();
    }
}
