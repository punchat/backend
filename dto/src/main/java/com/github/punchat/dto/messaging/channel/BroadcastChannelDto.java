package com.github.punchat.dto.messaging.channel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BroadcastChannelDto {
    private Long id;
    private String name;
    private Privacy privacy;
}
