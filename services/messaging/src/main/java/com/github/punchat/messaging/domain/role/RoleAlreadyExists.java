package com.github.punchat.messaging.domain.role;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RoleAlreadyExists extends RuntimeException {
    public RoleAlreadyExists(String name) {
        super(String.format("Role with name %s already exists", name));
    }
}
