package com.github.punchat.messaging.user;

import com.github.punchat.messaging.channel.Channel;
import com.github.punchat.messaging.abstractentity.AbstractIdentifiableObject;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString(of = {"id"})
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addressees")
public class User extends AbstractIdentifiableObject {

    @ManyToMany
    @JoinTable( name = "members",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "channel_id"))
    private List<Channel> channels;

}
