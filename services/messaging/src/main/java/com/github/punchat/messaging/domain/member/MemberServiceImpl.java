package com.github.punchat.messaging.domain.member;

import com.github.punchat.messaging.domain.ResourceNotFoundException;
import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.role.*;
import com.github.punchat.messaging.domain.user.User;
import com.github.punchat.messaging.id.IdService;
import com.github.punchat.messaging.security.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final AuthService auth;
    private final MemberRepository memberRepository;
    private final MemberFinder finder;
    private final RoleFinder roleFinder;
    private final IdService idService;

    @Override
    public Set<Member> getMembers(BroadcastChannel channel) {
        return memberRepository.findByChannel(channel);
    }

    @Override
    public Member createAdmin(BroadcastChannel channel, User user) {
        Role owner = roleFinder.owner(channel);
        return create(user, channel, owner);
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
    public void delete(Member member) {
        User authorized = auth.getAuthorizedUser();
        Member authorizedMember;
        try {
            authorizedMember = finder.byUserAndChannel(authorized, member.getChannel());
        } catch (ResourceNotFoundException e) {
            throw new NotAMemberException(authorized.getId(), member.getChannel().getName());
        }

        if (member.getRole().getName().equals(DefaultRoles.OWNER)) {
            throw new OwnerExclusionException(authorized.getId(), member.getId());
        }
        if (authorizedMember.getRole().getPermissions().contains(Permission.CAN_EXCLUDE_USERS)) {
            memberRepository.delete(member);
        }
    }

    @Override
    public Member getAuthorizedUserAsChannelMembers(BroadcastChannel channel) {
        return finder.byUserAndChannel(auth.getAuthorizedUser(), channel);
    }
}
