package com.github.punchat.messaging.security;

import com.github.punchat.messaging.domain.UserInfoIsNotProvidedException;
import com.github.punchat.messaging.domain.user.User;

public interface AuthService {
    boolean isTrusted();

    User getAuthorizedUser() throws UserInfoIsNotProvidedException;
}
