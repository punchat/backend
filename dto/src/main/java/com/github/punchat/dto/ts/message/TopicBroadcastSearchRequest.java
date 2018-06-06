package com.github.punchat.dto.ts.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicBroadcastSearchRequest {
    private Long channelId;
    private String text;
}
