package com.github.punchat.notifications.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
    String NEW_ACCESS_CODE_EVENTS = "newAccessCodeEvents";

    String NEW_WORKSPACE_INVITATION_EVENTS = "newWorkspaceInvitationEvents";

    String NEW_EMAILS_EVENTS = "newEmailsEvents";

    @Input(NEW_ACCESS_CODE_EVENTS)
    SubscribableChannel newAccessCodeEvents();

    @Input(NEW_WORKSPACE_INVITATION_EVENTS)
    SubscribableChannel newWorkspaceInvitationEvents();

    @Output(NEW_EMAILS_EVENTS)
    MessageChannel newEmailsEvents();
}
