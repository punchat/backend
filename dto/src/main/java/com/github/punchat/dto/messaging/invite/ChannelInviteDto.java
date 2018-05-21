package com.github.punchat.dto.messaging.invite;

import com.github.punchat.dto.messaging.channel.BroadcastChannelResponse;
import com.github.punchat.dto.messaging.member.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChannelInviteDto {
    private Long id;
    private MemberDto sender;
    private BroadcastChannelResponse channel;
    private MemberDto recipient;
}
