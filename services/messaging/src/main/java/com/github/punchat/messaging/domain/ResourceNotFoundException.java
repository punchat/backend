package com.github.punchat.messaging.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceNotFoundException extends RuntimeException {
    private final static String FORMAT = "resource %s with identifier %s is not found";

    public ResourceNotFoundException(String resourceName, Object identifier) {
        super(String.format(FORMAT, resourceName, identifier));
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
