package com.github.punchat.dto.messaging.message;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BroadcastMessageRequest {
    private Long channelId;
    private String text;
    private List<Long> addresses;
}
