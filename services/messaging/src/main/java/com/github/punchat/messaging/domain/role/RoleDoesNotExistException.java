package com.github.punchat.messaging.domain.role;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RoleDoesNotExistException extends RuntimeException {
    public RoleDoesNotExistException(String name) {
        super(String.format("Role %s does not exist", name));
    }
}
