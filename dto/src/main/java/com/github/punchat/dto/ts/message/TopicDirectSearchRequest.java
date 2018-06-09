package com.github.punchat.dto.ts.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicDirectSearchRequest {
    private Long senderId;
    private Long receiverId;
    private String text;
}
