package com.github.punchat.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InviteToChannelEvent {
    private long senderUserId;
    private long recipientUserId;
    private long channelId;
    private LocalDateTime creationTime;
}
