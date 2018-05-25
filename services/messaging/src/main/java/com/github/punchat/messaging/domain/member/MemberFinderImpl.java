package com.github.punchat.messaging.domain.member;

import com.github.punchat.messaging.domain.ResourceNotFoundException;
import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberFinderImpl implements MemberFinder {
    private MemberRepository repository;

    @Override
    public Member byId(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("member", id));
    }

    @Override
    public Member byUserAndChannel(User user, BroadcastChannel channel) {
        return repository.findByUserAndChannel(user, channel)
                .orElseThrow(() -> new ResourceNotFoundException("member",
                                new UserAndChannelIdentifier(user.getId(), channel.getId())));
    }

    private static class UserAndChannelIdentifier {
        private final Long userId;
        private final Long channelId;

        private UserAndChannelIdentifier(Long userId, Long channelId) {
            this.userId = userId;
            this.channelId = channelId;
        }

        @Override
        public String toString() {
            return String.format("user: %s, channel: %s", userId, channelId);
        }
    }
}
