package com.github.punchat.messaging.domain.role;

import com.github.punchat.messaging.domain.channel.BroadcastChannelFinder;
import com.github.punchat.messaging.domain.member.MemberFinder;
import com.github.punchat.messaging.id.IdService;
import com.github.punchat.messaging.security.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
public class RoleConfiguration {
    private final AuthService authService;
    private final IdService idService;
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleConfiguration(AuthService authService, IdService idService, RoleRepository roleRepository, RoleMapper roleMapper) {
        log.info("creating role configuration");
        this.authService = authService;
        this.idService = idService;
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Bean
    @Profile("unsecured")
    public RoleService unsecuredRoleService(BroadcastChannelFinder bcFinder) {
        log.info("creating unsecured role service");
        return new RoleServiceImpl(idService, roleMapper, roleRepository, bcFinder);
    }

    @Bean
    @Profile("secured")
    public RoleService securedRoleService(BroadcastChannelFinder bcFinder, MemberFinder memberFinder) {
        log.info("creating secured role service");
        return new SecuredRoleService(
                new RoleServiceImpl(idService, roleMapper, roleRepository, bcFinder),
                authService,
                memberFinder,
                bcFinder,
                roleMapper
        );
    }


}
