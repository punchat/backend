package com.github.punchat.messaging.domain.channel;

import com.github.punchat.messaging.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DirectChannelRepository extends JpaRepository<DirectChannel, Long> {
    Optional<DirectChannel> findByUser(User user);
}
