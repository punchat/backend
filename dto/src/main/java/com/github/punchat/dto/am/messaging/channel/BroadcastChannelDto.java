package com.github.punchat.dto.am.messaging.channel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BroadcastChannelDto {
    private String name;
    private Privacy privacy;
}
