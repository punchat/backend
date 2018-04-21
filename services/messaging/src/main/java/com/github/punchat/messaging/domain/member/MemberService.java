package com.github.punchat.messaging.domain.member;

import java.util.Set;

public interface MemberService {
    Set<Member> findByChannel(Long channelId);
}
