package com.github.punchat.messaging.domain.message;

import com.github.punchat.dto.messaging.message.DirectMessageResponse;
import com.github.punchat.dto.messaging.user.UserDto;
import com.github.punchat.messaging.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DirectMessageMapper {
    @Mappings({
            @Mapping(source = "senderUser", target = "sender"),
            @Mapping(source = "channel.user", target = "receiver")
    })
    DirectMessageResponse toResponse(DirectMessage msg);

    UserDto userToUserDto(User user);
}
