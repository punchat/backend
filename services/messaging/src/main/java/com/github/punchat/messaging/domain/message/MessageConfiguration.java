package com.github.punchat.messaging.domain.message;

import com.github.punchat.messaging.domain.member.MemberRepository;
import com.github.punchat.messaging.security.AuthService;
<<<<<<< HEAD
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {
=======
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MessageConfiguration {
    public MessageConfiguration() {
        log.info("creating message configuration");
    }

>>>>>>> dev
    @Bean
    public BroadcastMessageFinder broadcastMessageFinder(AuthService authService, MemberRepository memberRepository, BroadcastMessageRepository repo) {
        return new SecuredBroadcastMessageFinder(
                new BroadcastMessageFinderImpl(repo),
                authService,
                memberRepository
        );
    }

    @Bean
    public DirectMessageFinder directMessageFinder(AuthService authService, DirectMessageRepository repo) {
        return new SecuredDirectMessageFinder(
                new DirectMessageFinderImpl(repo),
                authService
        );
    }
}
