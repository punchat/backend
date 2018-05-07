package com.github.punchat.am.events;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface AccessCodes {
    String ACCESS_CODE_GENERATED_EVENTS = "accessCodeGeneratedEvents";

    @Output(ACCESS_CODE_GENERATED_EVENTS)
    MessageChannel accessCodeGeneratedEvents();
}
