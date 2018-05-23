package com.github.punchat.messaging.domain.member;

import com.github.punchat.messaging.domain.role.ForbiddenException;

public class NotAMemberException extends ForbiddenException {
    private final static String FORMAT = "user %s is not a member of channel %s";

    public NotAMemberException(Long userId, String channelName) {
        super(String.format(FORMAT, userId, channelName));
    }
}
