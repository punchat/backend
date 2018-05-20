package com.github.punchat.messaging.domain.role;

public class AbsentPermissionException extends ForbiddenException {
    public AbsentPermissionException(Long userId, Permission permission) {
        super(String.format("User with id %d " +
                "has'nt permission %s for this operation.", userId, permission.toString()));
    }
}
