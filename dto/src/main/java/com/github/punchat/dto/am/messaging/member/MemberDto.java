package com.github.punchat.dto.am.messaging.member;

import com.github.punchat.dto.am.messaging.role.RoleDto;
import com.github.punchat.dto.am.messaging.channel.BroadcastChannelDto;
import com.github.punchat.dto.am.messaging.user.UserDto;
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
