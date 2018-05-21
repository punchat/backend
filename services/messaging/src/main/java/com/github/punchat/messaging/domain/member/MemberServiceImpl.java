package com.github.punchat.messaging.domain.member;

import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.channel.BroadcastChannelFinder;
import com.github.punchat.messaging.domain.role.DefaultRoles;
import com.github.punchat.messaging.domain.role.Role;
import com.github.punchat.messaging.domain.role.RoleRepository;
import com.github.punchat.messaging.domain.user.User;
import com.github.punchat.messaging.domain.user.UserRepository;
import com.github.punchat.messaging.id.IdService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final BroadcastChannelFinder finder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final IdService idService;


    @Override
    public Member createAdmin(BroadcastChannel channel, User user) {
        Role owner = roleRepository.findByName(DefaultRoles.OWNER);
        return create(user.getId(), channel.getId(), owner.getId());
    }

    @Override
    public Member create(User user, BroadcastChannel channel, Role role) {
        Member member = new Member();
        member.setId(idService.next());
        member.setChannel(channel);
        member.setUser(user);
        member.setRole(role);
        return memberRepository.save(member);
    }

    @Override
    public Member create(Long userId, Long channelId, Long roleId) {
        User user = userRepository.getOne(userId);
        BroadcastChannel channel = finder.byId(channelId);
        Role role = roleRepository.getOne(roleId);
        return create(user, channel, role);
    }

    @Override
    public Member findByUserAndChannel(Long userId, String channelName) {
        User user = userRepository.getOne(userId);
        BroadcastChannel channel = finder.byName(channelName);
        return findByUserAndChannel(user, channel);
    }

    @Override
    public Member findByUserAndChannel(User user, BroadcastChannel channel) {
        return memberRepository.findByUserAndChannel(user, channel);
    }

    @Override
    public Set<Member> findByChannel(Long channelId) {
        BroadcastChannel channel = finder.byId(channelId);
        return memberRepository.findByChannel(channel);
    }
}
