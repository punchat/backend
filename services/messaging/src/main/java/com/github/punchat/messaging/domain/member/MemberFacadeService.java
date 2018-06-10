package com.github.punchat.messaging.domain.member;

import com.github.punchat.dto.messaging.member.MemberResponse;

import java.util.List;

public interface MemberFacadeService {
    List<MemberResponse> getMembers(Long channelId);

    void delete(Long id);

    MemberResponse getAuthorizedUserAsChannelMembers(Long channelId);

    MemberResponse join(Long channelId);
}
