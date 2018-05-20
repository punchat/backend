package com.github.punchat.messaging.domain.invite;

import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.member.Member;
import com.github.punchat.messaging.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"recipient_user_id", "channel_id"}),
        name = "CHANNEL_INVITE")
@Entity
public class ChannelInvite {

    @ManyToOne
    @JoinColumn(name = "sender_user_id")
    private Member sender;

    @ManyToOne
    @JoinColumn(name = "channel_id", nullable = false)
    private BroadcastChannel channel;

    @ManyToOne
    @JoinColumn(name = "recipient_user_id", nullable = false)
    private User recipient;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private State state;
}
