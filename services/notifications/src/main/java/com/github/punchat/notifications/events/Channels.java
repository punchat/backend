package com.github.punchat.notifications.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {
    String ACCESS_CODE_GENERATED_EVENTS = "accessCodeGeneratedEvents";

    String INVITE_TO_WORKSPACE_EVENTS = "inviteToWorkspaceEvents";

    String NEW_EMAILS_EVENTS = "newEmailsEvents";

    @Input(ACCESS_CODE_GENERATED_EVENTS)
    SubscribableChannel accessCodeGeneratedEvents();

    @Input(INVITE_TO_WORKSPACE_EVENTS)
    SubscribableChannel inviteToWorkspaceEvents();

    @Output(NEW_EMAILS_EVENTS)
    MessageChannel newEmailsEvents();
}
