package com.github.punchat.messaging.domain.message;

import com.github.punchat.dto.messaging.member.MemberDto;
import com.github.punchat.dto.messaging.message.BroadcastMessageResponse;
import com.github.punchat.messaging.domain.member.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel="spring")
public interface MessageMapper {
    @Mappings(
            @Mapping(source = "senderMember", target = "sender")
    )
    BroadcastMessageResponse toResponse(BroadcastMessage message);

    MemberDto memberToMemberDto(Member member);
}
