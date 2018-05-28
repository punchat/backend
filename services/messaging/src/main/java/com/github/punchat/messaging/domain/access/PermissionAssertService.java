package com.github.punchat.messaging.domain.access;

public interface PermissionAssertService {
    PermissionAssert.UserStageBuilder checkThat();
}
