package com.github.punchat.dto.messaging.message;

import com.github.punchat.dto.messaging.user.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ToString
public class DirectMessageResponse {
    private Long id;
    private UserDto sender;
    private UserDto receiver;
    private String text;
    private LocalDateTime createdOn;
}
