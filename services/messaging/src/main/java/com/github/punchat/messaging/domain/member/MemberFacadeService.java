package com.github.punchat.messaging.domain.member;

import com.github.punchat.dto.messaging.member.MemberResponse;

import java.util.Set;

public interface MemberFacadeService {
    Set<MemberResponse> getMembers(Long channelId);

    void delete(Long id);

    MemberResponse getAuthorizedUserAsChannelMembers(Long channelId);

    MemberResponse join(Long channelId);
}
