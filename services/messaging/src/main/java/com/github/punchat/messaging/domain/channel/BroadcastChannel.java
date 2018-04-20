package com.github.punchat.messaging.domain.channel;

import com.github.punchat.messaging.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@Setter
@Entity
@DiscriminatorValue("broadcast")
public class BroadcastChannel extends Channel {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "channels")
    private List<User> members;
}
