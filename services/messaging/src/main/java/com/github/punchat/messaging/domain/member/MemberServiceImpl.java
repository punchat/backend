package com.github.punchat.messaging.domain.member;

import com.github.punchat.messaging.domain.channel.BroadcastChannelRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MemberServiceImpl implements MemberService {
    private final BroadcastChannelRepository broadcastChannelRepository;
    private final MemberRepository repository;

    public MemberServiceImpl(BroadcastChannelRepository broadcastChannelRepository, MemberRepository repository) {
        this.broadcastChannelRepository = broadcastChannelRepository;
        this.repository = repository;
    }

    @Override
    public Set<Member> findByChannel(Long channelId) {
        return broadcastChannelRepository.findById(channelId)
                .map(repository::findByChannel).orElse(new HashSet<>());
    }
}
