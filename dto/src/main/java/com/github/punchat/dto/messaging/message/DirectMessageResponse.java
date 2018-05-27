package com.github.punchat.dto.messaging.message;

import com.github.punchat.dto.messaging.resource.ResourceDto;
import com.github.punchat.dto.messaging.user.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class DirectMessageResponse {
    private Long id;
    private UserDto sender;
    private UserDto receiver;
    private ResourceDto resource;
}
