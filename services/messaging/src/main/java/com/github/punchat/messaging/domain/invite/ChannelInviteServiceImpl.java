package com.github.punchat.messaging.domain.invite;

import com.github.punchat.log.Trace;
import com.github.punchat.messaging.security.AuthService;
import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.channel.BroadcastChannelFinder;
import com.github.punchat.messaging.domain.member.MemberService;
import com.github.punchat.messaging.domain.role.AbsentPermissionException;
import com.github.punchat.messaging.domain.role.Permission;
import com.github.punchat.messaging.domain.role.RoleRepository;
import com.github.punchat.messaging.domain.user.User;
import com.github.punchat.messaging.domain.user.UserService;
import com.github.punchat.messaging.id.IdService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Trace
@Service
@AllArgsConstructor
public class ChannelInviteServiceImpl implements ChannelInviteService {
    private final ChannelInviteRepository repository;
    private final AuthService authService;
    private final MemberService memberService;
    private final UserService userService;
    private final BroadcastChannelFinder bFinder;
    private final IdService idService;
    private final RoleRepository roleRepository;

    @Override
    public ChannelInvite createChannelInvite(String channelName, Long recipientId, Long roleId) {
        User authorized = authService.getAuthorizedUser();
        if (memberService.findByUserAndChannel(authorized.getId(), channelName).getRole().
                getPermissions().contains(Permission.CAN_INVITE_USERS)) {
            User recipient = userService.getUser(recipientId);
            BroadcastChannel channel = bFinder.byName(channelName);
            if (!repository.existsByChannelAndRecipient(channel, recipient)) {
                ChannelInvite channelInvite = new ChannelInvite();
                channelInvite.setId(idService.next());
                channelInvite.setSender(memberService.findByUserAndChannel(authorized.getId(), channelName));
                channelInvite.setChannel(bFinder.byName(channelName));
                channelInvite.setRecipient(userService.getUser(recipientId));
                channelInvite.setState(State.CREATED);
                channelInvite.setRole(roleRepository.getOne(roleId));
                return repository.save(channelInvite);
            } else {
                throw new ChannelInviteAlreadyCreatedException(channelName, recipientId);
            }
        } else {
            throw new AbsentPermissionException(recipientId, Permission.CAN_INVITE_USERS);
        }
    }

    @Override
    public Set<Long> getUserChannelsInvited(Long userId) {
        User user = userService.getUser(userId);
        return repository.findByRecipientAndState(user, State.CREATED)
                .stream()
                .map(ChannelInvite::getId)
                .collect(Collectors.toSet());
    }

    @Override
    public ChannelInvite acceptChannelInvite(String channelName) {
        User recipient = authService.getAuthorizedUser();
        BroadcastChannel channel = bFinder.byName(channelName);
        if (repository.existsByChannelAndRecipient(channel, recipient)) {
            ChannelInvite channelInvite =
                    repository.findByChannelAndRecipient(channel, recipient);
            channelInvite.setState(State.ACCEPTED);
            memberService.create(recipient.getId(), channel.getId(), channelInvite.getRole().getId());
            return repository.save(channelInvite);
        } else {
            throw new ChannelInviteDoNotFoundException(channelName, recipient.getId());
        }
    }
}
