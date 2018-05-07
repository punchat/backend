package com.github.punchat.am.events;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Members {
    String NEW_MEMBER_IN_CHANNEL = "newMemberInChannel";

    @Output(NEW_MEMBER_IN_CHANNEL)
    MessageChannel newMemberInChannel();
}
