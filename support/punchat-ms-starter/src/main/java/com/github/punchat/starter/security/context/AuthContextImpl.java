package com.github.punchat.starter.security.context;

import com.github.punchat.starter.security.auth.UserPayload;
import com.github.punchat.starter.security.auth.Auth;
import com.github.punchat.starter.security.auth.AuthImpl;
import com.github.punchat.starter.security.auth.ClientInfo;
import com.github.punchat.starter.security.auth.UserInfo;
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
