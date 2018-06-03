package com.github.punchat.dto.messaging.message;

import com.github.punchat.dto.messaging.channel.BroadcastChannelResponse;
import com.github.punchat.dto.messaging.member.MemberResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BroadcastMessageResponse {
    private Long id;
    private MemberResponse sender;
    private String text;
    private List<MemberResponse> addressees;
    private BroadcastChannelResponse channel;
}
