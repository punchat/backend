package com.github.punchat.messaging.domain.member;

import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.channel.BroadcastChannelRepository;
import com.github.punchat.messaging.domain.user.UserRepository;
import com.github.punchat.messaging.domain.role.Role;
import com.github.punchat.messaging.domain.role.RoleRepository;
import com.github.punchat.messaging.domain.user.User;
import com.github.punchat.messaging.domain.user.UserRepository;
import com.github.punchat.messaging.id.IdService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final BroadcastChannelRepository broadcastChannelRepository;
    private final UserRepository userRepository;
    private final MemberRepository memberRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final IdService idService;

    public MemberServiceImpl(MemberRepository memberRepository, BroadcastChannelRepository broadcastChannelRepository, UserRepository userRepository, RoleRepository roleRepository, IdService idService) {
        this.memberRepository = memberRepository;
        this.broadcastChannelRepository = broadcastChannelRepository;
        this.userRepository = userRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.idService = idService;
    }

    @Override
    public Member join(Long userId, Long channelId) {
        User user = userRepository.getOne(userId);
        BroadcastChannel channel = broadcastChannelRepository.getOne(channelId);
        Member member = memberRepository.findByUserAndChannel(user, channel);
        member.setState(State.ACCEPTED);
        return member;
    }

    @Override
    public Member invite(Long userId, Long channelId, Long roleId) {
        Member member = new Member();
        member.setId(idService.next());
        BroadcastChannel channel = broadcastChannelRepository.getOne(channelId);
        member.setChannel(channel);
        User user = userRepository.getOne(userId);
        member.setUser(user);
        Role role = roleRepository.getOne(roleId);
        member.setRole(role);
        return memberRepository.save(member);
    }

    @Override
    public Member findByUser(Long userId) {
        return userRepository.findById(userId)
                .map(repository::findByUser).orElse(new Member());
    }

    @Override
    public Set<Member> findByChannel(Long channelId) {
        return broadcastChannelRepository.findById(channelId)
                .map(memberRepository::findByChannel).orElse(new HashSet<>());
    }
}
