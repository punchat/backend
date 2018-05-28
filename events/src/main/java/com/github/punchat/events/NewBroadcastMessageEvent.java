package com.github.punchat.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewBroadcastMessageEvent {
    private Long channelId;
    private Long messageId;
}
