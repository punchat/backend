package com.github.punchat.messaging.domain.member;

import com.github.punchat.messaging.domain.channel.BroadcastChannelRepository;
import com.github.punchat.messaging.domain.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MemberServiceImpl implements MemberService {
    private final BroadcastChannelRepository broadcastChannelRepository;
    private final UserRepository userRepository;
    private final MemberRepository repository;

    public MemberServiceImpl(BroadcastChannelRepository broadcastChannelRepository, UserRepository userRepository, MemberRepository repository) {
        this.broadcastChannelRepository = broadcastChannelRepository;
        this.userRepository = userRepository;
        this.repository = repository;
    }

    @Override
    public Member findByUser(Long userId) {
        return userRepository.findById(userId)
                .map(repository::findByUser).orElse(new Member());
    }

    @Override
    public Set<Member> findByChannel(Long channelId) {
        return broadcastChannelRepository.findById(channelId)
                .map(repository::findByChannel).orElse(new HashSet<>());
    }
}
