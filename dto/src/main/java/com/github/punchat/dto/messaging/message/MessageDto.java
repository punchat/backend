package com.github.punchat.dto.messaging.message;

import com.github.punchat.dto.messaging.user.UserDto;
import com.github.punchat.dto.messaging.resource.ResourceDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageDto {
    private UserDto sender;
    private ResourceDto resource;
}
