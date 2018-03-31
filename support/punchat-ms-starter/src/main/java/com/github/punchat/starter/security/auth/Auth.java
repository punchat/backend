package com.github.punchat.starter.security.auth;

import com.github.punchat.starter.UserInfo;

import java.util.Optional;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface Auth {
    Optional<UserInfo> getUserInfo();

    ClientInfo getClient();

    Set<String> getScope();
}
