package com.github.punchat.messaging.domain.invite;

import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ChannelInviteRepository extends JpaRepository<ChannelInvite, Long> {
    ChannelInvite findByChannelAndRecipient(BroadcastChannel channel, User recipient);

    Set<ChannelInvite> findByRecipientAndState(User recipient, State state);

    boolean existsByChannelAndRecipient(BroadcastChannel channel, User recipient);
}
