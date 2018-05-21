package com.github.punchat.messaging.domain.channel;

import com.github.punchat.messaging.domain.user.User;
import com.github.punchat.starter.uaa.client.auth.Auth;
import com.github.punchat.starter.uaa.client.auth.UserInfo;
import com.github.punchat.starter.uaa.client.context.AuthContext;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SecurityUtils {
    public static void withUser(AuthContext authContext, User user) {
        Auth auth = mock(Auth.class);
        UserInfo info = new UserInfo(user.getId(), "test");
        when(auth.getUserInfo()).thenReturn(Optional.of(info));
        when(authContext.get()).thenReturn(auth);
    }
}
