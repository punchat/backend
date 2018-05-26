package com.github.punchat.messaging.domain.invite;

import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface ChannelInviteRepository extends JpaRepository<ChannelInvite, Long> {
    Optional<ChannelInvite> findByChannelAndRecipient(BroadcastChannel channel, User recipient);

    Set<ChannelInvite> findByRecipientAndState(User recipient, State state);

    Set<ChannelInvite> findByChannelAndState(BroadcastChannel channel, State state);

    boolean existsByChannelAndRecipient(BroadcastChannel channel, User recipient);

    @Query("select count (i)>0 from ChannelInvite ")
    boolean existsByChannelAndRecipient(Long channelId, Long recipientId);

    Set<ChannelInvite> findByChannel(BroadcastChannel channel);
}
