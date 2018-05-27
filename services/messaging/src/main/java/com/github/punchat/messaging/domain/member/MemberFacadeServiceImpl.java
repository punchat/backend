package com.github.punchat.messaging.domain.member;

import com.github.punchat.dto.messaging.member.MemberDto;
import com.github.punchat.log.Trace;
import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.channel.BroadcastChannelFinder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Trace
@Service
@AllArgsConstructor
public class MemberFacadeServiceImpl implements MemberFacadeService {
    private final MemberService service;
    private final MemberFinder finder;
    private final BroadcastChannelFinder channelFinder;
    private final MemberMapper mapper;

    @Override
    public Set<MemberDto> getMembers(Long channelId) {
        return map(service.getMembers(channelFinder.byId(channelId)));
    }

    @Override
    public void delete(Long id) {
        service.delete(finder.byId(id));
    }

    @Override
    public MemberDto getAuthorizedUserAsChannelMembers(Long channelId) {
        BroadcastChannel channel = channelFinder.byId(channelId);
        return map(service.getAuthorizedUserAsChannelMembers(channel));
    }

    @Override
    public MemberDto join(Long channelId) {
        return map(service.join(channelFinder.byId(channelId)));
    }

    private MemberDto map(Member member) {
        return mapper.toResponse(member);
    }

    private Set<MemberDto> map(Set<Member> members) {
        return members.stream()
                .map(this::map)
                .collect(Collectors.toSet());
    }
}
