package com.github.punchat.messaging.domain.user;

public interface UserService {
    User createEmptyUser(Long id);

    User getUser(Long id);
}
