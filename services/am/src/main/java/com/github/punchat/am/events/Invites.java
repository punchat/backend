package com.github.punchat.am.events;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Invites {
        String INVITE_TO_CHANNEL_CREATED_EVENTS = "inviteToChannelCreatedEvents";
        String INVITE_TO_CHANNEL_ACCEPTED_EVENTS = "inviteToChannelAcceptedEvents";
        String INVITE_TO_WORKSPACE_CREATED_EVENTS = "inviteToWorkspaceCreatedEvents";
        String ACCESS_CODE_GENERATED_EVENTS = "accessCodeGeneratedEvents";

        @Output(INVITE_TO_CHANNEL_CREATED_EVENTS)
        MessageChannel inviteToChannelCreatedEvents();

        @Output(INVITE_TO_CHANNEL_ACCEPTED_EVENTS)
        MessageChannel inviteToChannelAcceptedEvents();

        @Output(INVITE_TO_WORKSPACE_CREATED_EVENTS)
        MessageChannel inviteToWorkspaceCreatedEvents();

        @Output(ACCESS_CODE_GENERATED_EVENTS)
        MessageChannel accessCodeGeneratedEvents();
}
