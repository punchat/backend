package com.github.punchat.messaging.domain.message;

import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.member.Member;
import com.github.punchat.messaging.domain.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString(callSuper = true)
@DiscriminatorValue("broadcast")
public class BroadcastMessage extends Message {
    @ManyToOne
    @JoinColumn(name = "broadcast_channel_id")
    private BroadcastChannel channel;

    @ManyToOne
    @JoinColumn(name = "member_sender_id")
    /*
    https://hibernate.atlassian.net/browse/HHH-12539?attachmentOrder=asc
    we can't use field name such as in DirectChannel. Because hibernate will try to
    find common class for User and Member
     */
    private Member senderMember;

    @ManyToMany
    @JoinTable(name = "addresses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "message_id"))
    private Set<Member> addressees;
}
