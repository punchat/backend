package com.github.punchat.messaging.domain.member;

import java.util.Set;

public interface MemberFacadeService {
    Set<Member> getMembers(Long channelId);

    void delete(Long userId, Long channelId);
}
