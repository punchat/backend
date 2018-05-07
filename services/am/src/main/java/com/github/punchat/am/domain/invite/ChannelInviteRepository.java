package com.github.punchat.am.domain.invite;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelInviteRepository extends JpaRepository<ChannelInvite, Long> {
    ChannelInvite findByRecipientUserIdAndChannelId(Long recipientUserId, Long channelId);

    boolean existsByChannelId(Long channelId);
}
