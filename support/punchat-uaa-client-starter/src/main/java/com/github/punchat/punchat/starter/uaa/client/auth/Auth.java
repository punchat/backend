package com.github.punchat.punchat.starter.uaa.client.auth;

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
