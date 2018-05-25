package com.github.punchat.messaging.domain.channel;

import com.github.punchat.dto.messaging.channel.BroadcastChannelRequest;
import com.github.punchat.messaging.domain.member.Member;
import com.github.punchat.messaging.domain.member.MemberFinder;
import com.github.punchat.messaging.domain.member.MemberService;
import com.github.punchat.messaging.domain.role.AbsentPermissionException;
import com.github.punchat.messaging.domain.role.Permission;
import com.github.punchat.messaging.domain.role.RoleService;
import com.github.punchat.messaging.domain.user.User;
import com.github.punchat.messaging.id.IdService;
import com.github.punchat.messaging.security.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@AllArgsConstructor
public class ChannelServiceImpl implements ChannelService {
    private final AuthService authService;
    private final BroadcastChannelRepository broadcastChannelRepository;
    private final IdService idService;
    private final ChannelMapper channelMapper;
    private final MemberService memberService;
    private final RoleService roleService;
    private final MemberFinder memberFinder;
    private final BroadcastChannelFinder bFinder;

    @Override
    @Transactional
    public BroadcastChannel create(BroadcastChannelRequest payload) {
        User user = authService.getAuthorizedUser();
        BroadcastChannel channel = channelMapper.fromRequest(payload);
        channel.setId(idService.next());
        channel = broadcastChannelRepository.save(channel);
        roleService.createDefaultRoles(channel);
        memberService.createAdmin(channel, user);
        return channel;
    }

    @Override
    public BroadcastChannel update(Long id, BroadcastChannelRequest request) {
        BroadcastChannel channel = bFinder.byId(id);
        channelMapper.updateChannel(request, channel);
        return broadcastChannelRepository.save(channel);
    }

    @Override
    public Set<BroadcastChannel> getAuthorizedUserChannels() {
        return getUserChannels(authService.getAuthorizedUser());
    }

    @Override
    public Set<BroadcastChannel> getUserChannels(User user) {
        return broadcastChannelRepository.findUserChannels(user);
    }

    @Override
    public void delete(Long id) {
        User user = authService.getAuthorizedUser();
        BroadcastChannel channel = bFinder.byId(id);
        Member member = memberFinder.byUserAndChannel(user, channel);
        if (member.getRole().getPermissions().contains(Permission.CAN_DELETE_CHANNEL)) {
            broadcastChannelRepository.deleteById(id);
        } else {
            throw new AbsentPermissionException(user.getId(), Permission.CAN_DELETE_MESSAGES);
        }
    }
}
