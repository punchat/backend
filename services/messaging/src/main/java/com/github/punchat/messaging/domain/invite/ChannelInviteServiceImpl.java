package com.github.punchat.messaging.domain.invite;

import com.github.punchat.log.Trace;
import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.channel.ChannelService;
import com.github.punchat.messaging.domain.member.MemberService;
import com.github.punchat.messaging.domain.role.AbsentPermissionException;
import com.github.punchat.messaging.domain.role.Permission;
import com.github.punchat.messaging.domain.role.RoleRepository;
import com.github.punchat.messaging.domain.user.User;
import com.github.punchat.messaging.domain.user.UserService;
import com.github.punchat.messaging.id.IdService;
import com.github.punchat.starter.uaa.client.context.AuthContext;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Trace
@Service
public class ChannelInviteServiceImpl implements ChannelInviteService {
    private final ChannelInviteRepository repository;
    private final AuthContext authContext;
    private final MemberService memberService;
    private final UserService userService;
    private final ChannelService channelService;
    private final IdService idService;
    private final RoleRepository roleRepository;

    public ChannelInviteServiceImpl(ChannelInviteRepository repository, AuthContext authContext, MemberService memberService, UserService userService, ChannelService channelService, IdService idService, RoleRepository roleRepository) {
        this.repository = repository;
        this.authContext = authContext;
        this.memberService = memberService;
        this.userService = userService;
        this.channelService = channelService;
        this.idService = idService;
        this.roleRepository = roleRepository;
    }

    @Override
    public ChannelInvite createChannelInvite(String channelName, Long recipientId, Long roleId) {
        Long authUserId = authContext.get().getUserInfo().get().getUserId();
        if (memberService.findByUserAndChannel(authUserId, channelName).getRole().
                getPermissions().contains(Permission.CAN_INVITE_USERS)) {
            User recipient = userService.getUser(recipientId);
            BroadcastChannel channel = channelService.getBroadcastChannelByName(channelName);
            if (!repository.existsByChannelAndRecipient(channel, recipient)) {
                ChannelInvite channelInvite = new ChannelInvite();
                channelInvite.setId(idService.next());
                channelInvite.setSender(memberService.findByUserAndChannel(authUserId, channelName));
                channelInvite.setChannel(channelService.getBroadcastChannelByName(channelName));
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
        Long recipientId = authContext.get().getUserInfo().get().getUserId();
        BroadcastChannel channel = channelService.getBroadcastChannelByName(channelName);
        User recipient = userService.getUser(recipientId);
        if (repository.existsByChannelAndRecipient(channel, recipient)) {
            ChannelInvite channelInvite =
                    repository.findByChannelAndRecipient(channel, recipient);
            channelInvite.setState(State.ACCEPTED);
            memberService.create(recipientId, channel.getId(), channelInvite.getRole().getId());
            return repository.save(channelInvite);
        } else {
            throw new ChannelInviteDoNotFoundException(channelName, recipientId);
        }
    }
}
