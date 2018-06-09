package com.github.punchat.dto.messaging.message;

import com.github.punchat.dto.messaging.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DirectMessageResponse {
    private Long id;
    private UserDto sender;
    private UserDto receiver;
    private String text;
}
