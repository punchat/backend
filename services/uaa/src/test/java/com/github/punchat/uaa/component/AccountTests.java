package com.github.punchat.uaa.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.punchat.uaa.account.AccountService;
import com.github.punchat.uaa.account.ChangePasswordPayload;
import com.github.punchat.uaa.utils.OAuthUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.core.StringContains.containsString;

/**
 * @author Alex Ivchenko
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
@ContextConfiguration(classes = ComponentTestConfig.class)
public class AccountTests {
    @Autowired
    private AccountService accountService;

    @Autowired
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        accountService.create("user", "pass");
    }

    @Test
    public void givenWrongPassword_whenChangingPassword_thenBadRequestWithExplanation() throws Exception {
        ChangePasswordPayload payload = new ChangePasswordPayload("wrong", "new");
        ObjectMapper mapper = new ObjectMapper();
        String token = OAuthUtils.obtainAccessToken(mvc, "user", "pass");
        mvc
                .perform(MockMvcRequestBuilders.put("/accounts/@me/password")
                        .header("Authorization", "Bearer " + token)
                        .content(mapper.writeValueAsString(payload))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(containsString("passwords")));
    }
}
