package com.github.punchat.messaging.domain.member;

import com.github.punchat.dto.messaging.member.MemberResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface MemberMapper {
    MemberResponse toResponse(Member member);
}
