package com.github.punchat.messaging.domain.message;

import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@DiscriminatorValue("broadcast")
public class BroadcastMessage extends Message {
    @ManyToOne
    @JoinColumn(name = "channel_id")
    private BroadcastChannel channel;

    @ManyToMany
    @JoinTable(name = "addresses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "message_id"))
    private List<User> addressees;
}
