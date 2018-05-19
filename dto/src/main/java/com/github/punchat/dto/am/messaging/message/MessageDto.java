package com.github.punchat.dto.am.messaging.message;

import com.github.punchat.dto.am.messaging.user.UserDto;
import com.github.punchat.dto.am.messaging.resource.ResourceDto;
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
