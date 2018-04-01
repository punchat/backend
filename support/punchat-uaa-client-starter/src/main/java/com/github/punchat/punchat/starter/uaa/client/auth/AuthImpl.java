package com.github.punchat.punchat.starter.uaa.client.auth;

import lombok.ToString;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@ToString(of = {"userInfo", "clientInfo"} )
public class AuthImpl implements Auth {
    private final UserInfo userInfo;
    private final ClientInfo clientInfo;
    private final Set<String> scope;

    public AuthImpl(UserInfo userInfo, ClientInfo clientInfo, Set<String> scope) {
        if (scope == null) {
            this.scope = new HashSet<>();
        } else {
            this.scope = new HashSet<>(scope);
        }
        if (clientInfo == null) {
            throw new NullPointerException("client info cannot be null");
        }
        this.userInfo = userInfo;
        this.clientInfo = clientInfo;
    }

    @Override
    public Optional<UserInfo> getUserInfo() {
        return Optional.ofNullable(userInfo);
    }

    @Override
    public ClientInfo getClient() {
        return clientInfo;
    }

    @Override
    public Set<String> getScope() {
        return Collections.unmodifiableSet(scope);
    }
}
