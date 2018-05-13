package com.github.punchat.uaa;

import com.github.punchat.uaa.registration.AccountManagementService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UaaApplicationTests {
    @MockBean
    AccountManagementService amService;
    @Test
    public void contextLoads() {
    }
}
