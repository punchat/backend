package com.github.punchat.punchat.starter.uaa.client.context;

import com.github.punchat.punchat.starter.uaa.client.auth.UserPayload;
import com.github.punchat.punchat.starter.uaa.client.auth.Auth;
import com.github.punchat.punchat.starter.uaa.client.auth.AuthImpl;
import com.github.punchat.punchat.starter.uaa.client.auth.ClientInfo;
import com.github.punchat.punchat.starter.uaa.client.auth.UserInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Alex Ivchenko
 */
@Slf4j
public class AuthContextImpl implements AuthContext {
    private final com.github.alexivchenko.spring.uaa.client.AuthContext<UserPayload> delegate;

    public AuthContextImpl(com.github.alexivchenko.spring.uaa.client.AuthContext<UserPayload> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Auth get() {
        UserPayload payload = delegate.auth();
        UserInfo userInfo = null;
        if (payload.getUser() != null) {
            Long id = Long.parseLong(payload.getUser().getId());
            userInfo = new UserInfo(id, payload.getUsername());
        }

        return new AuthImpl(userInfo, new ClientInfo(payload.getClientId()), payload.getScope());
    }

}
