package com.github.punchat.messaging.dao;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString(of = {"id", "type"})
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

    @OneToMany(mappedBy = "channel_id")
    private List<Message> messages;

    @ManyToMany(mappedBy = "channels")
    private List<User> users;
}
