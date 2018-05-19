package com.github.punchat.messaging.domain.role;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PermissionDoesNotContains extends RuntimeException{
    public PermissionDoesNotContains(String permission, String name) {
        super(String.format("Permission %s does not contains in role with name %s", permission, name));
    }
}
