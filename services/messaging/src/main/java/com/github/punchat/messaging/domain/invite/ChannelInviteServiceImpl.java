package com.github.punchat.messaging.domain.invite;

import com.github.punchat.log.Trace;
import com.github.punchat.messaging.domain.channel.ChannelService;
import com.github.punchat.messaging.domain.member.MemberService;
import com.github.punchat.messaging.domain.role.AbsentPermissionException;
import com.github.punchat.messaging.domain.role.Permission;
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

    public ChannelInviteServiceImpl(ChannelInviteRepository repository, AuthContext authContext, MemberService memberService, UserService userService, ChannelService channelService, IdService idService) {
        this.repository = repository;
        this.authContext = authContext;
        this.memberService = memberService;
        this.userService = userService;
        this.channelService = channelService;
        this.idService = idService;
    }

    @Override
    public ChannelInvite createChannelInvite(String channelName, Long recipientId) {
        Long authUserId = authContext.get().getUserInfo().get().getUserId();
        if (memberService.findByUser(authUserId).getRole().
                getPermissions().contains(Permission.CAN_INVITE_USERS)) {
            if (!repository.existsByChannel_NameAndRecipient_Id(channelName, recipientId)) {
                ChannelInvite channelInvite = new ChannelInvite();
                channelInvite.setId(idService.next());
                channelInvite.setSender(memberService.findByUser(authUserId));
                channelInvite.setChannel(channelService.getBroadcastChannelByName(channelName));
                channelInvite.setRecipient(userService.getUser(recipientId));
                channelInvite.setState(State.CREATED);
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
        return repository.findByRecipient_IdAndState(userId, State.CREATED)
                .stream()
                .map(ChannelInvite::getId)
                .collect(Collectors.toSet());
    }

    @Override
    public ChannelInvite acceptChannelInvite(String channelName) {
        Long recipientId = authContext.get().getUserInfo().get().getUserId();
        if (repository.existsByChannel_NameAndRecipient_Id(channelName, recipientId)) {
            ChannelInvite channelInvite =
                    repository.findByChannel_NameAndRecipient_Id(channelName, recipientId);
            channelInvite.setState(State.ACCEPTED);
            return repository.save(channelInvite);
        } else {
            throw new ChannelInviteDoNotFoundException(channelName, recipientId);
        }
    }
}
