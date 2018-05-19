package com.github.punchat.messaging.domain.invite;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ChannelInviteRepository extends JpaRepository<ChannelInvite, Long> {
    ChannelInvite findByRecipientUserIdAndChannelId(Long recipientUserId, Long channelId);

    Set<ChannelInvite> findByRecipientUserIdAndState(Long recipientUserId, State state);

    boolean existsByRecipientUserIdAndChannelId(Long recipientUserId, Long channelId);
}
