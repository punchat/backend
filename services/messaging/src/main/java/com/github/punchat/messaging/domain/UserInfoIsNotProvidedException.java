package com.github.punchat.messaging.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserInfoIsNotProvidedException extends RuntimeException {
    public UserInfoIsNotProvidedException() {
        super("request does not contain user info");
    }
}
