package com.github.punchat.messaging.domain.channel;

import com.github.punchat.messaging.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectChannelRepository extends JpaRepository<DirectChannel, Long> {
    DirectChannel findByUser(User user);
}
