package com.github.punchat.messaging.domain.user;

import com.github.punchat.messaging.domain.AbstractIdentifiableObject;
import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.channel.DirectChannel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends AbstractIdentifiableObject {

    @ManyToMany
    @JoinTable(name = "members",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "channel_id"))
    private List<BroadcastChannel> channels;

    @OneToOne
    private DirectChannel channel;

}
