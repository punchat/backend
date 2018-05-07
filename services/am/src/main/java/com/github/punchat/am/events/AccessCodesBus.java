package com.github.punchat.am.events;

import com.github.punchat.events.AccessCodeGeneratedEvent;
import org.springframework.messaging.MessageChannel;

public interface AccessCodesBus{
    void publishAccessCodeGenerated(AccessCodeGeneratedEvent event);
}
