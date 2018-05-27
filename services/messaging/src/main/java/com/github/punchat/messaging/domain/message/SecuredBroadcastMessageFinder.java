package com.github.punchat.messaging.domain.message;

import com.github.punchat.log.Trace;
import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.member.MemberRepository;
import com.github.punchat.messaging.domain.member.NotAMemberException;
import com.github.punchat.messaging.domain.user.User;
import com.github.punchat.messaging.security.AuthService;
import lombok.AllArgsConstructor;

import java.util.List;

@Trace
@AllArgsConstructor
public class SecuredBroadcastMessageFinder implements BroadcastMessageFinder {
    private final BroadcastMessageFinder unsecured;
    private final AuthService authService;
    private final MemberRepository memberRepository;

    @Override
    public BroadcastMessage byId(Long id) {
        BroadcastMessage msg = unsecured.byId(id);
        checkAccess(msg.getChannel());
        return msg;
    }

    @Override
    public List<BroadcastMessage> getLast(BroadcastChannel channel, int limit) {
        checkAccess(channel);
        return unsecured.getLast(channel, limit);
    }

    @Override
    public List<BroadcastMessage> getBefore(BroadcastChannel channel, Long id, int limit) {
        checkAccess(channel);
        return unsecured.getBefore(channel, id, limit);
    }

    @Override
    public List<BroadcastMessage> getAfter(BroadcastChannel channel, Long id, int limit) {
        checkAccess(channel);
        return unsecured.getAfter(channel, id, limit);
    }

    private void checkAccess(BroadcastChannel channel) {
        if (authService.isTrusted()) {
            return;
        }
        User authorized = authService.getAuthorizedUser();
        if (!memberRepository.existsByUserAndChannel(authorized, channel)) {
            throw new NotAMemberException(authorized.getId(), channel.getName());
        }
    }
}
