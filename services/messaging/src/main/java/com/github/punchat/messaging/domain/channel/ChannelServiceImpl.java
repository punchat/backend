package com.github.punchat.messaging.domain.channel;

import com.github.punchat.messaging.domain.member.Member;
import com.github.punchat.messaging.domain.member.MemberRepository;
import com.github.punchat.messaging.domain.role.Role;
import com.github.punchat.messaging.domain.user.User;
import com.github.punchat.messaging.domain.user.UserService;
import com.github.punchat.messaging.id.IdService;
import com.github.punchat.starter.uaa.client.context.AuthContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChannelServiceImpl implements ChannelService {
    private final AuthContext authContext;
    private final BroadcastChannelRepository broadcastChannelRepository;
    private final DirectChannelRepository directChannelRepository;
    private final UserService userService;
    private final IdService idService;
    private final MemberRepository memberRepository;

    public ChannelServiceImpl(AuthContext authContext, BroadcastChannelRepository broadcastChannelRepository, DirectChannelRepository directChannelRepository, UserService userService, IdService idService, MemberRepository memberRepository) {
        this.authContext = authContext;
        this.broadcastChannelRepository = broadcastChannelRepository;
        this.directChannelRepository = directChannelRepository;
        this.userService = userService;
        this.idService = idService;
        this.memberRepository = memberRepository;
    }

    @Override
    public DirectChannel getDirectChannel(long userId) {
        User user = userService.getUser(userId);
        return directChannelRepository.findByUser(user);
    }

    @Override
    @Transactional
    public BroadcastChannel createBroadcastChannel(BroadcastChannel channel) {
        Long userId = authContext.get().getUserInfo().get().getUserId();
        User user = userService.getUser(userId);
        channel.setId(idService.next());
        channel = broadcastChannelRepository.save(channel);
        Member admin = createAdmin(channel, user);
        memberRepository.save(admin);
        return channel;
    }

    @Override
    public BroadcastChannel get(Long id) {
        return broadcastChannelRepository.getOne(id);
    }

    private Member createAdmin(BroadcastChannel channel, User user) {
        Member member = new Member();
        member.setId(idService.next());
        member.setUser(user);
        member.setChannel(channel);
        member.setRole(new Role("ROLE_ADMIN"));
        return member;
    }
}
