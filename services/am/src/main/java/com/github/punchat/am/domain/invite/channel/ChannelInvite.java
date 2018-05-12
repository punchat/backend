package com.github.punchat.am.domain.invite.channel;

import com.github.punchat.am.domain.invite.Invite;
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
        name = "channel")
@Entity
public class ChannelInvite extends Invite {

    @Column(name = "recipient_user_id", nullable = false)
    private Long recipientUserId;

    @Column(name = "channel_id", nullable = false)
    private Long channelId;
}
