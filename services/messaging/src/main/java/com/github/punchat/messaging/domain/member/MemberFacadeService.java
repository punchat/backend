package com.github.punchat.messaging.domain.member;

import com.github.punchat.dto.messaging.member.MemberDto;

import java.util.Set;

public interface MemberFacadeService {
    Set<MemberDto> getMembers(Long channelId);

    void delete(Long id);

    MemberDto getAuthorizedUserAsChannelMembers(Long channelId);

    MemberDto join(Long channelId);
}
