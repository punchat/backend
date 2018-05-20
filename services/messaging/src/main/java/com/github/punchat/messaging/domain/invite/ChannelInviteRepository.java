package com.github.punchat.messaging.domain.invite;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelInviteRepository extends JpaRepository<ChannelInvite, Long> {
    ChannelInvite findByChannel_NameAndRecipient_Id(String channelName, Long recipientId);

    boolean existsByChannel_NameAndRecipient_Id(String channelName, Long recipientId);
}
