package com.github.punchat.messaging.domain.member;

import java.util.Set;

public interface MemberService {
    Member join(Long userId, Long channelId);

    Member invite(Long userId, Long channelId, Long roleId);

    Set<Member> findByChannel(Long channelId);
}
