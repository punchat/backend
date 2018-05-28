package com.github.punchat.dto.messaging.message;

import com.github.punchat.dto.messaging.member.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BroadcastMessageResponse {
    private Long id;
    private MemberDto sender;
    private String text;
}
