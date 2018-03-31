package com.github.punchat.starter.security.context;

import com.github.punchat.starter.security.auth.Auth;

/**
 * @author Alex Ivchenko
 */
public interface AuthContext {
    Auth get();
}
