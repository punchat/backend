package com.github.punchat.messaging.domain.user;

public interface UserService {
    User createUser(Long id);

    User getUser(Long id);
}
