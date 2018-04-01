package com.github.punchat.punchat.starter.uaa.client.context;

import com.github.punchat.punchat.starter.uaa.client.auth.Auth;

/**
 * @author Alex Ivchenko
 */
public interface AuthContext {
    Auth get();
}
