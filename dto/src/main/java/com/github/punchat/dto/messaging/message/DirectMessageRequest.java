package com.github.punchat.dto.messaging.message;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class DirectMessageRequest {
    private Long receiverId;
    private String text;
}
