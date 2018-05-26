package com.github.punchat.messaging.domain.message;

import com.github.punchat.log.Trace;
import com.github.punchat.messaging.domain.ResourceNotFoundException;
import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.member.MemberRepository;
import com.github.punchat.messaging.domain.member.NotAMemberException;
import com.github.punchat.messaging.domain.user.User;
import com.github.punchat.messaging.security.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Trace
@Service
@AllArgsConstructor
public class BroadcastMessageFinderImpl implements BroadcastMessageFinder {
    private final AuthService authService;
    private final BroadcastMessageRepository repository;
    private final MemberRepository memberRepository;

    @Override
    public BroadcastMessage byId(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("message", id));
    }

    @Override
    public List<BroadcastMessage> getLast(BroadcastChannel channel, int limit) {
        checkThatCurrentUserIsMember(channel);
        return repository.findLast(channel, PageRequest.of(0, limit)).getContent();
    }

    @Override
    public List<BroadcastMessage> getBefore(BroadcastChannel channel, Long id, int limit) {
        checkThatCurrentUserIsMember(channel);
        BroadcastMessage msg = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("message", id));
        return repository.findBefore(channel, msg.getCreatedOn(), PageRequest.of(0, limit)).getContent();
    }

    @Override
    public List<BroadcastMessage> getAfter(BroadcastChannel channel, Long id, int limit) {
        checkThatCurrentUserIsMember(channel);
        BroadcastMessage msg = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("message", id));
        return repository.findAfter(channel, msg.getCreatedOn(), PageRequest.of(0, limit)).getContent();
    }

    private void checkThatCurrentUserIsMember(BroadcastChannel channel) {
        User authorized = authService.getAuthorizedUser();
        if (!memberRepository.existsByUserAndChannel(authorized, channel)) {
            throw new NotAMemberException(authorized.getId(), channel.getName());
        }
    }
}
