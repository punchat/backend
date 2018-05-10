package com.github.punchat.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewMemberInChannelEvent {
    private long userId;
    private long recipientUserId;
    private long channelId;
    private long invitationId;
}
