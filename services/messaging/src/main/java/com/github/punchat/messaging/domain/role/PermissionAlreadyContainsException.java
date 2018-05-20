package com.github.punchat.messaging.domain.role;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PermissionAlreadyContainsException extends RuntimeException {
    public PermissionAlreadyContainsException(String permission, String name) {
        super(String.format("Permission %s already contains in role with name %s", permission, name));
    }
}
