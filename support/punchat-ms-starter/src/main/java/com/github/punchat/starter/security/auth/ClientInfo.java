package com.github.punchat.starter.security.auth;

import lombok.Getter;
import lombok.ToString;

/**
 * @author Alex Ivchenko
 */
@ToString
@Getter
public class ClientInfo {
    private final String clientId;

    public ClientInfo(String clientId) {
        this.clientId = clientId;
    }
}
