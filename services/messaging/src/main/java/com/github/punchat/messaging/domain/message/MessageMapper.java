package com.github.punchat.messaging.domain.message;

import com.github.punchat.dto.messaging.member.MemberDto;
import com.github.punchat.dto.messaging.message.MessageDto;
import com.github.punchat.dto.messaging.resource.ResourceDto;
import com.github.punchat.messaging.domain.member.Member;
import com.github.punchat.messaging.domain.resource.Resource;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface MessageMapper {
    MessageDto messageToMessageDto(Message message);

    MemberDto memberToMemberDto(Member member);

    ResourceDto resourceToResourceDto(Resource resource);
}
