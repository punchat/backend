package com.github.punchat.messaging.domain.member;

import java.util.Set;

public interface MemberService {
    Member create(Long userId, Long channelId, Long roleId);

    Set<Member> findByChannel(Long channelId);

    Member findByUserAndChannel(Long userId, String channelName);
}
