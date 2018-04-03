package com.github.punchat.uaa.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alex Ivchenko
 */
public class AccountTest {
    private Account account;
    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new ObjectMapper();
    }

    @Test
    public void jsonShouldNotContainPasswordField() throws Exception {
        account = new Account(1L, "user", "pass");
        String json = mapper.writeValueAsString(account);
        assertThat(json)
                .doesNotContain("password")
                .doesNotContain("pass");
    }
}