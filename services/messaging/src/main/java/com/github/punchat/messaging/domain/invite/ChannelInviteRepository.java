package com.github.punchat.messaging.domain.invite;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ChannelInviteRepository extends JpaRepository<ChannelInvite, Long> {
    ChannelInvite findByChannel_NameAndRecipient_Id(String channelName, Long recipientId);

    Set<ChannelInvite> findByRecipient_IdAndState(Long recipientId, State state);

    boolean existsByChannel_NameAndRecipient_Id(String channelName, Long recipientId);
}
