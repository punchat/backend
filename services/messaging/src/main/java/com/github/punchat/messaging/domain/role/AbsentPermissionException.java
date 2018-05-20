package com.github.punchat.messaging.domain.role;

import netscape.security.ForbiddenTargetException;

public class AbsentPermissionException extends ForbiddenTargetException {
    private final static String MESSAGE = "passwords do not match";

    public AbsentPermissionException(Long userId, Permission permission) {
        super(String.format("User with id %d " +
                "has'nt permission %s for this operation.", userId, permission.toString()));
    }
}
