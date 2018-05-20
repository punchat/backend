package com.github.punchat.dto.messaging.invite;

import com.github.punchat.dto.messaging.channel.BroadcastChannelDto;
import com.github.punchat.dto.messaging.member.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChannelInviteDto {
    private MemberDto sender;
    private BroadcastChannelDto channel;
    private MemberDto recipient;
}
