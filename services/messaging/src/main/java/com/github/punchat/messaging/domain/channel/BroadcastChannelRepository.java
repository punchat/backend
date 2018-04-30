package com.github.punchat.messaging.domain.channel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BroadcastChannelRepository extends JpaRepository<BroadcastChannel, Long> {
}
