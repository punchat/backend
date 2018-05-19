package com.github.punchat.dto.messaging.member;

import com.github.punchat.dto.messaging.role.RoleDto;
import com.github.punchat.dto.messaging.channel.BroadcastChannelDto;
import com.github.punchat.dto.messaging.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDto {
    private UserDto user;
    private BroadcastChannelDto channel;
    private RoleDto role;
}
