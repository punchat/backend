package com.github.punchat.messaging.domain.member;

import com.github.punchat.log.Trace;
import com.github.punchat.messaging.domain.access.PermissionAssertService;
import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.role.DefaultRoles;
import com.github.punchat.messaging.domain.role.Permission;
import com.github.punchat.messaging.domain.role.Role;
import com.github.punchat.messaging.domain.role.RoleFinder;
import com.github.punchat.messaging.domain.user.User;
import com.github.punchat.messaging.id.IdService;
import com.github.punchat.messaging.security.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Trace
@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final AuthService auth;
    private final MemberRepository memberRepository;
    private final MemberFinder finder;
    private final RoleFinder roleFinder;
    private final IdService idService;
    private final PermissionAssertService permissions;

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
        permissions.checkThat()
                .authorizedUser()
                .hasPermission(Permission.CAN_EXCLUDE_USERS)
                .in(member.getChannel());
        if (member.getRole().getName().equals(DefaultRoles.OWNER)) {
            User authorized = auth.getAuthorizedUser();
            throw new OwnerExclusionException(authorized.getId(), member.getId());
        }
        memberRepository.delete(member);
    }

    @Override
    public Member getAuthorizedUserAsChannelMembers(BroadcastChannel channel) {
        return finder.byUserAndChannel(auth.getAuthorizedUser(), channel);
    }
}
