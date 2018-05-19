package com.github.punchat.messaging.domain.channel;

import com.github.punchat.messaging.domain.member.MemberRepository;
import com.github.punchat.messaging.domain.member.MemberService;
import com.github.punchat.messaging.domain.member.MemberServiceImpl;
import com.github.punchat.messaging.domain.role.DefaultRoleAutoCreator;
import com.github.punchat.messaging.domain.role.RoleRepository;
import com.github.punchat.messaging.domain.user.UserRepository;
import com.github.punchat.messaging.domain.user.UserService;
import com.github.punchat.messaging.domain.user.UserServiceImpl;
import com.github.punchat.messaging.id.IdService;
import com.github.punchat.starter.uaa.client.context.AuthContext;
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
        return new MemberServiceImpl(memberRepository, broadcastChannelRepository, userRepository, roleRepository, idService());
    }

    @Bean
    public ChannelService channelService() {
        return new ChannelServiceImpl(authContext, broadcastChannelRepository, directChannelRepository, userService(), idService(), memberRepository, roleRepository);
    }

    @Bean
    public IdService idService() {
        return new MockIdService();
    }

    @Bean
    public DefaultRoleAutoCreator defaultRoleAutoCreator() {
        return new DefaultRoleAutoCreator(roleRepository, idService());
    }
}
