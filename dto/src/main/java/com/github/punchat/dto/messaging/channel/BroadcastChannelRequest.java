package com.github.punchat.dto.messaging.channel;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BroadcastChannelRequest {
    private String name;
    private Privacy privacy;
}
