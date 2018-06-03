package com.github.punchat.dto.messaging.member;

import com.github.punchat.dto.messaging.channel.BroadcastChannelResponse;
import com.github.punchat.dto.messaging.role.RoleResponse;
import com.github.punchat.dto.messaging.user.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberResponse {
    private Long id;
    private UserDto user;
    private BroadcastChannelResponse channel;
    private RoleResponse role;
}
