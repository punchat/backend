package com.github.punchat.dto.messaging.message;

import com.github.punchat.dto.messaging.member.MemberDto;
import com.github.punchat.dto.messaging.resource.ResourceDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageDto {
    private MemberDto sender;
    private ResourceDto resource;
}
