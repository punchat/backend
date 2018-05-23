package com.github.punchat.messaging.domain.member;

import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.channel.BroadcastChannelFinder;
import com.github.punchat.messaging.domain.user.User;
import com.github.punchat.messaging.domain.user.UserFinder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class MemberFacadeServiceImpl implements MemberFacadeService {
    private final MemberService service;
    private final UserFinder userFinder;
    private final BroadcastChannelFinder channelFinder;

    @Override
    public Set<Member> getMembers(Long channelId) {
        return service.getMembers(channelFinder.byId(channelId));
    }

    @Override
    public void delete(Long userId, Long channelId) {
        User user = userFinder.byId(userId);
        BroadcastChannel channel = channelFinder.byId(channelId);
        service.delete(user, channel);
    }
}
