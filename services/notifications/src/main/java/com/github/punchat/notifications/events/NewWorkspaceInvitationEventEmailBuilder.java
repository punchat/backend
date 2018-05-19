package com.github.punchat.notifications.events;

import com.github.punchat.events.InviteToWorkspaceEvent;
import com.github.punchat.events.NewEmailCreatedEvent;
import com.github.punchat.log.Trace;
import org.springframework.stereotype.Component;

@Trace
@Component
public class NewWorkspaceInvitationEventEmailBuilder implements NewEmailEventBuilder<InviteToWorkspaceEvent> {
    private final static String subject = "punchat";

    @Override
    public NewEmailCreatedEvent create(InviteToWorkspaceEvent event) {
        String email = event.getEmail();
        long id = event.getSenderUserId();
        String text = String.format("User %s has invited you to workspace", id);
        return new NewEmailCreatedEvent(email, subject, text);
    }
}
