package com.github.punchat.messaging.domain.invite;

import com.github.punchat.log.Trace;
import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.member.Member;
import com.github.punchat.messaging.domain.member.MemberFinder;
import com.github.punchat.messaging.domain.member.MemberService;
import com.github.punchat.messaging.domain.role.AbsentPermissionException;
import com.github.punchat.messaging.domain.role.ForbiddenException;
import com.github.punchat.messaging.domain.role.Permission;
import com.github.punchat.messaging.domain.role.Role;
import com.github.punchat.messaging.domain.user.User;
import com.github.punchat.messaging.id.IdService;
import com.github.punchat.messaging.security.AuthService;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Trace
@Service
@Setter
@NoArgsConstructor
public class ChannelInviteServiceImpl implements ChannelInviteService {
    private AuthService auth;
    private IdService ids;
    private ChannelInviteRepository repo;
    private MemberService memberService;
    private MemberFinder memberFinder;

    @Autowired
    public ChannelInviteServiceImpl(AuthService auth, IdService ids, ChannelInviteRepository repo, MemberService memberService, MemberFinder memberFinder) {
        this.auth = auth;
        this.ids = ids;
        this.repo = repo;
        this.memberService = memberService;
        this.memberFinder = memberFinder;
    }

    @Override
    @Transactional
    public ChannelInvite createChannelInvite(BroadcastChannel channel, User recipient, Role role) {
        User authorized = auth.getAuthorizedUser();
        assertInvitationDoesNotExists(channel, recipient);
        Member sender = memberFinder.byUserAndChannel(authorized, channel);
        assertSenderHasPermissions(sender, role);
        return doCreateChannelInvitation(sender, recipient, role);
    }

    private void assertSenderHasPermissions(Member sender, Role role) {
        if (!sender.getRole().getPermissions().contains(Permission.CAN_INVITE_USERS)) {
            throw new AbsentPermissionException(sender.getUser().getId(), Permission.CAN_INVITE_USERS);
        }
        for (Permission permission : role.getPermissions()) {
            if (!sender.getRole().getPermissions().contains(permission)) {
                throw new AbsentPermissionException(sender.getUser().getId(), permission);
            }
        }
    }

    private ChannelInvite doCreateChannelInvitation(Member sender, User recipient, Role role) {
        ChannelInvite channelInvite = new ChannelInvite();
        channelInvite.setId(ids.next());
        channelInvite.setSender(sender);
        channelInvite.setChannel(sender.getChannel());
        channelInvite.setRecipient(recipient);
        channelInvite.setState(State.CREATED);
        channelInvite.setRole(role);
        return repo.save(channelInvite);
    }

    private void assertInvitationDoesNotExists(BroadcastChannel channel, User recipient) {
        if (repo.existsByChannelAndRecipient(channel, recipient)) {
            throw new ChannelInviteAlreadyCreatedException(channel.getName(), recipient.getId());
        }
    }

    private Set<ChannelInvite> getUserInvitations(User user) {
        return repo.findByRecipientAndState(user, State.CREATED);
    }

    @Override
    public Set<ChannelInvite> getAuthorizedUserInvites() {
        return getUserInvitations(auth.getAuthorizedUser());
    }

    @Override
    public ChannelInvite acceptInvite(ChannelInvite invite) {
        User authorized = auth.getAuthorizedUser();
        if (authorized.equals(invite.getRecipient())) {
            throw new ForbiddenException(String.format("user %s cannot accept invitation of user %s",
                    authorized.getId(), invite.getRecipient().getId()));
        }
        invite.setState(State.ACCEPTED);
        memberService.create(authorized, invite.getChannel(), invite.getRole());
        return repo.save(invite);
    }
}
