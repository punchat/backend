package com.github.punchat.messaging.dao;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString(of = {"id"})
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

    @OneToOne(mappedBy = "user_id")
    private Message message;
}
