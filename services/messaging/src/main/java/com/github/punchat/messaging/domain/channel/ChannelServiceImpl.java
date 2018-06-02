package com.github.punchat.messaging.domain.channel;

import com.github.punchat.dto.messaging.channel.BroadcastChannelRequest;
import com.github.punchat.log.Trace;
import com.github.punchat.messaging.domain.member.Member;
import com.github.punchat.messaging.domain.member.MemberFinder;
import com.github.punchat.messaging.domain.member.MemberService;
import com.github.punchat.messaging.domain.role.AbsentPermissionException;
import com.github.punchat.messaging.domain.role.Permission;
import com.github.punchat.messaging.domain.role.RoleFinder;
import com.github.punchat.messaging.domain.role.RoleService;
import com.github.punchat.messaging.domain.user.User;
import com.github.punchat.messaging.id.IdService;
import com.github.punchat.messaging.security.AuthService;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Trace
@Service
@Setter
@NoArgsConstructor
public class ChannelServiceImpl implements ChannelService {
    private AuthService authService;
    private IdService idService;
    private BroadcastChannelRepository broadcastChannelRepository;
    private ChannelMapper channelMapper;
    private MemberService memberService;
    private RoleService roleService;
    private MemberFinder memberFinder;
    private BroadcastChannelFinder bFinder;
    private RoleFinder roleFinder;

    @Autowired
    public ChannelServiceImpl(AuthService authService,
                              IdService idService,
                              BroadcastChannelRepository broadcastChannelRepository,
                              ChannelMapper channelMapper,
                              MemberService memberService,
                              RoleService roleService,
                              MemberFinder memberFinder,
                              BroadcastChannelFinder bFinder,
                              RoleFinder roleFinder) {
        this.authService = authService;
        this.idService = idService;
        this.broadcastChannelRepository = broadcastChannelRepository;
        this.channelMapper = channelMapper;
        this.memberService = memberService;
        this.roleService = roleService;
        this.memberFinder = memberFinder;
        this.bFinder = bFinder;
        this.roleFinder = roleFinder;
    }

    @Override
    @Transactional
    public BroadcastChannel create(BroadcastChannelRequest payload) {
        User user = authService.getAuthorizedUser();
        return createFor(user, payload);
    }

    @Override
    @Transactional
    public BroadcastChannel createFor(User user, BroadcastChannelRequest payload) {
        BroadcastChannel channel = channelMapper.fromRequest(payload);
        channel.setId(idService.next());
        channel = broadcastChannelRepository.save(channel);
        roleService.createDefaultRoles(channel);
        channel.setDefaultRole(roleFinder.defaultRole(channel));
        channel = broadcastChannelRepository.save(channel);
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

    @Override
    public Set<BroadcastChannel> getAllPublicChannels() {
        return new HashSet<>(broadcastChannelRepository.findByPrivacy(Privacy.PUBLIC));
    }
}
