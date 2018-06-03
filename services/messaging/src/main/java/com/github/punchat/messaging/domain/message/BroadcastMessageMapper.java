package com.github.punchat.messaging.domain.message;

import com.github.punchat.dto.messaging.member.MemberResponse;
import com.github.punchat.dto.messaging.message.BroadcastMessageResponse;
import com.github.punchat.messaging.domain.member.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Set;

@Mapper(componentModel="spring")
public interface BroadcastMessageMapper {
    @Mappings(
            @Mapping(source = "senderMember", target = "sender")
    )
    BroadcastMessageResponse toResponse(BroadcastMessage message);

    List<MemberResponse> membersToMembersResponses(Set<Member> members);
}
