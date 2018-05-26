package com.github.punchat.messaging.domain.message;

import com.github.punchat.messaging.domain.channel.DirectChannel;
import com.github.punchat.messaging.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
@DiscriminatorValue("direct")
public class DirectMessage extends Message {
    @ManyToOne
    @JoinColumn(name = "direct_channel_id")
    private DirectChannel channel;

    @ManyToOne
    @JoinColumn(name = "user_sender_id")
    /*
    https://hibernate.atlassian.net/browse/HHH-12539?attachmentOrder=asc
    we can't use field name such as in BroadcastChannel. Because hibernate will try to
    find common class for User and Member
     */
    private User senderUser;
}
