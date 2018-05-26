package com.github.punchat.messaging.domain.member;

import com.github.punchat.dto.messaging.member.MemberDto;
import com.github.punchat.dto.messaging.role.RoleDto;
import com.github.punchat.messaging.domain.role.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface MemberMapper {
    MemberDto toResponse(Member member);

    RoleDto roleToRoleDto(Role role);
}
