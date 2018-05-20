package com.github.punchat.messaging.domain.member;

import java.util.Set;

public interface MemberService {
    Member findByUser(Long userId);

    Set<Member> findByChannel(Long channelId);
}
