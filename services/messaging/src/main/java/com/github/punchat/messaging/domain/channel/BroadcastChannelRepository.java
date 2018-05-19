package com.github.punchat.messaging.domain.channel;

import com.github.punchat.messaging.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BroadcastChannelRepository extends JpaRepository<BroadcastChannel, Long> {

    @Query("select m.channel from Member m where m.user = :user")
    List<BroadcastChannel> findUserChannels(@Param("user") User user);
}
