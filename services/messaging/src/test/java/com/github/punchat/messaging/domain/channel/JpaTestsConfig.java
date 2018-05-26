package com.github.punchat.messaging.domain.channel;

import com.github.punchat.messaging.MockIdService;
import com.github.punchat.messaging.domain.access.PermissionAssertService;
import com.github.punchat.messaging.domain.access.PermissionAssertServiceImpl;
import com.github.punchat.messaging.domain.member.*;
import com.github.punchat.messaging.domain.role.*;
import com.github.punchat.messaging.domain.user.*;
import com.github.punchat.messaging.id.IdService;
import com.github.punchat.messaging.security.AuthService;
import com.github.punchat.messaging.security.AuthServiceImpl;
import com.github.punchat.starter.uaa.client.context.AuthContext;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class JpaTestsConfig {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DirectChannelRepository directChannelRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BroadcastChannelRepository broadcastChannelRepository;
    @Autowired
    private RoleRepository roleRepository;

    @MockBean
    private AuthContext authContext;

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userRepository, directChannelRepository, idService());
    }

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(authService(), memberRepository, memberFinder(), roleFinder(), idService(), permissionAssertService());
    }

    @Bean
    public PermissionAssertService permissionAssertService() {
        return new PermissionAssertServiceImpl(authService(), memberFinder());
    }

    @Bean
    public MemberFinder memberFinder() {
        return new MemberFinderImpl(memberRepository);
    }

    @Bean
    public RoleFinder roleFinder() {
        return new RoleFinderImpl(roleRepository);
    }

    @Bean
    public BroadcastChannelFinder broadcastChannelFinder() {
        return new BroadcastChannelFinderImpl(broadcastChannelRepository);
    }

    @Bean
    public ChannelService channelService() {
        return new ChannelServiceImpl(authService(), idService(), broadcastChannelRepository, channelMapper(), memberService(), roleService(), memberFinder(), broadcastChannelFinder(),roleFinder());
    }

    @Bean
    public RoleService roleService() {
        return new RoleServiceImpl(authService(), idService(), roleMapper(), roleRepository, broadcastChannelFinder(), memberFinder());
    }

    @Bean
    public RoleMapper roleMapper() {
        return Mappers.getMapper(RoleMapper.class);
    }

    @Bean
    public ChannelMapper channelMapper() {
        return Mappers.getMapper(ChannelMapper.class);
    }

    @Bean
    public UserFinder userFinder() {
        return new UserFinderImpl(userRepository);
    }

    @Bean
    public AuthService authService() {
        return new AuthServiceImpl(authContext, userRepository);
    }

    @Bean
    public IdService idService() {
        return new MockIdService();
    }
}
