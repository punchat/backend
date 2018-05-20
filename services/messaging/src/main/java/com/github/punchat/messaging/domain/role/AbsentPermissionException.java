package com.github.punchat.messaging.domain.role;

import netscape.security.ForbiddenTargetException;

public class AbsentPermissionException extends ForbiddenTargetException {
    public AbsentPermissionException(Long userId, Permission permission) {
        super(String.format("User with id %d " +
                "has'nt permission %s for this operation.", userId, permission.toString()));
    }
}
