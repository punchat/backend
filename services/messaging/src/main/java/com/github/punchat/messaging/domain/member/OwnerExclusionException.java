package com.github.punchat.messaging.domain.member;

import com.github.punchat.messaging.domain.role.ForbiddenException;

public class OwnerExclusionException extends ForbiddenException {
    private final static String FORMAT = "user %s is trying to exclude owner %s";

    public OwnerExclusionException(Long initiatorId, Long ownerId) {
        super(String.format(FORMAT, initiatorId, ownerId));
    }
}
