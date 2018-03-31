package com.github.punchat.starter.security.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@ToString
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPayload {
    @JsonProperty("user_name")
    private String username;
    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("scope")
    private Set<String> scope;
    @JsonProperty("user")
    private User user;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class User {
        private String id;
    }
}
