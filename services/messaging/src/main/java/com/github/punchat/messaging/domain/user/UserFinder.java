package com.github.punchat.messaging.domain.user;

public interface UserFinder {
    User byId(Long id);
}
