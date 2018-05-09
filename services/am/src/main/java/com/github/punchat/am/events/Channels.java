package com.github.punchat.am.events;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Channels {
    String ACCESS_CODE_GENERATED_EVENTS = "accessCodeGeneratedEvents";
    String INVITE_TO_CHANNEL_EVENTS = "inviteToChannelEvents";
    String INVITE_TO_WORKSPACE_EVENTS = "inviteToWorkspaceEvents";
    String NEW_MEMBER_IN_CHANNEL = "newMemberInChannel";

    @Output(NEW_MEMBER_IN_CHANNEL)
    MessageChannel newMemberInChannel();

    @Output(INVITE_TO_WORKSPACE_EVENTS)
    MessageChannel inviteToWorkspaceEvents();

    @Output(INVITE_TO_CHANNEL_EVENTS)
    MessageChannel inviteToChannelEvents();

    @Output(ACCESS_CODE_GENERATED_EVENTS)
    MessageChannel accessCodeGeneratedEvents();
}
