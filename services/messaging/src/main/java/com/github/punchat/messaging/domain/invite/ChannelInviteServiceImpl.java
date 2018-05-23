package com.github.punchat.messaging.domain.invite;

import com.github.punchat.log.Trace;
import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.member.Member;
import com.github.punchat.messaging.domain.member.MemberService;
import com.github.punchat.messaging.domain.role.AbsentPermissionException;
import com.github.punchat.messaging.domain.role.Permission;
import com.github.punchat.messaging.domain.role.Role;
import com.github.punchat.messaging.domain.user.User;
import com.github.punchat.messaging.id.IdService;
import com.github.punchat.messaging.security.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Trace
@Service
@AllArgsConstructor
public class ChannelInviteServiceImpl implements ChannelInviteService {
    private final ChannelInviteRepository repo;
    private final AuthService auth;
    private final MemberService members;
    private final IdService ids;

    @Override
    public ChannelInvite createChannelInvitation(BroadcastChannel channel, User recipient, Role role) {
        User authorized = auth.getAuthorizedUser();
        assertInvitationDoesNotExists(channel, recipient);
        Member sender = members.findByUserAndChannel(authorized, channel);
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
    public Set<ChannelInvite> getAuthorizedUserInvitations() {
        return getUserInvitations(auth.getAuthorizedUser());
    }

    @Override
    public ChannelInvite acceptChannelInvitation(BroadcastChannel channel) {
        User recipient = auth.getAuthorizedUser();
        ChannelInvite channelInvite = getInvite(channel, recipient);
        channelInvite.setState(State.ACCEPTED);
        members.create(recipient, channel, channelInvite.getRole());
        return repo.save(channelInvite);
    }

    private ChannelInvite getInvite(BroadcastChannel channel, User recipient) {
        return repo.findByChannelAndRecipient(channel, recipient).orElseThrow(() ->
                new ChannelInviteDoNotFoundException(channel.getName(), recipient.getId()));
    }
}
