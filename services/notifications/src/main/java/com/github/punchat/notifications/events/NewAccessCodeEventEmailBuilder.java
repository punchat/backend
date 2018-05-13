package com.github.punchat.notifications.events;

import com.github.punchat.events.AccessCodeGeneratedEvent;
import com.github.punchat.events.NewEmailCreatedEvent;
import com.github.punchat.log.Trace;
import org.springframework.stereotype.Component;

@Trace
@Component
public class NewAccessCodeEventEmailBuilder implements NewEmailEventBuilder<AccessCodeGeneratedEvent> {
    private final static String subject = "punchat";

    @Override
    public NewEmailCreatedEvent create(AccessCodeGeneratedEvent event) {
        String email = event.getEmail();
        String code = event.getCode();
        String text = String.format("code for punchat: %s", code);
        return new NewEmailCreatedEvent(email, subject, text);
    }
}
