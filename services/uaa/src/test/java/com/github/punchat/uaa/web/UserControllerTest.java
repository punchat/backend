package com.github.punchat.uaa.web;

import com.github.punchat.starter.web.ValidationErrorHandlingConfiguration;
import com.github.punchat.uaa.account.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;

/**
 * @author Alex Ivchenko
 */
@RunWith(SpringRunner.class)
@WebMvcTest(secure = false)
@Import(ValidationErrorHandlingConfiguration.class)
@MockBean(classes = AccountService.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void givenRightData_whenCreatingAccount_thenStatusIsCreated() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.post("/accounts")
                        .content("{\"username\": \"alex\", \"password\": \"pass\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void missNewPasswordField_whenChangingPassword_thenBadRequestWithExplainingMessage() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.put("/accounts/@me/password")
                        .content("{\"oldPassword\": \"old\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(containsString("newPassword")));
    }

    @Test
    public void missOldPasswordField_whenChangingPassword_thenBadRequestWithExplainingMessage() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.put("/accounts/@me/password")
                        .content("{\"newPassword\": \"new\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(containsString("oldPassword")));
    }

    @Test
    public void missUsernameField_whenCreatingAccount_thenBadRequestWithExplainingMessage() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.post("/accounts")
                        .content("{\"password\": \"pass\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(containsString("username")));
    }

    @Test
    public void missPasswordField_whenCreatingAccount_thenBadRequestWithExplainingMessage() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.post("/accounts")
                        .content("{\"username\": \"user\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(containsString("password")));
    }
}
