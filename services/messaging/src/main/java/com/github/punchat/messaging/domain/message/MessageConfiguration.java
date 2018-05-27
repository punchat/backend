package com.github.punchat.messaging.domain.message;

import com.github.punchat.messaging.domain.member.MemberRepository;
import com.github.punchat.messaging.security.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {
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
