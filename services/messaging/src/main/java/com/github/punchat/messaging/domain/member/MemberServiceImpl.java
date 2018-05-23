package com.github.punchat.messaging.domain.member;

import com.github.punchat.messaging.domain.ResourceNotFoundException;
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

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final AuthService auth;
    private final MemberRepository memberRepository;
    private final RoleFinder roleFinder;
    private final IdService idService;

    @Override
    public Set<Member> getMembers(BroadcastChannel channel) {
        return memberRepository.findByChannel(channel);
    }

    @Override
    public Member createAdmin(BroadcastChannel channel, User user) {
        Role owner = roleFinder.owner();
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
    public Member findByUserAndChannel(User user, BroadcastChannel channel) {
        return memberRepository.findByUserAndChannel(user, channel)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("member of %s channel and %s user not found", channel.getId(), user.getId())));
    }

    @Override
    public void delete(User user, BroadcastChannel channel) {
        User authorized = auth.getAuthorizedUser();
        Member authorizedMember;
        try {
            authorizedMember = findByUserAndChannel(authorized, channel);
        } catch (ResourceNotFoundException e) {
            throw new NotAMemberException(authorized.getId(), channel.getName());
        }
        Member toDelete = findByUserAndChannel(user, channel);
        if (toDelete.getRole().getName().equals(DefaultRoles.OWNER)) {
            throw new OwnerExclusionException(authorized.getId(), toDelete.getId());
        }
        if (authorizedMember.getRole().getPermissions().contains(Permission.CAN_EXCLUDE_USERS)) {
            memberRepository.delete(toDelete);
        }
    }
}
