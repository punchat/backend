package com.github.punchat.starter.security.auth;

import lombok.Getter;
import lombok.ToString;

/**
 * @author Alex Ivchenko
 */
@ToString
@Getter
public class UserInfo {
    private final Long userId;
    private final String username;

    public UserInfo(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }
}
