package com.github.punchat.dto.ts.message;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TopicSearchRequest {
    private Long channelId;
    private String text;
}
