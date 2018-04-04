package com.github.punchat.messaging.channel;

import com.github.punchat.messaging.abstractentity.AbstractIdentifiableObject;
import com.github.punchat.messaging.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString(of = {"id", "type"})
@EqualsAndHashCode(of = {"id", "type"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addressees")
public class Channel extends AbstractIdentifiableObject {

    @Column(name = "type")
    private ChannelType type;

    private enum ChannelType{
        Direct, Broadcast
    }

    @ManyToMany(mappedBy = "channels")
    private List<User> users;
}
