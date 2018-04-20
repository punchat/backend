package com.github.punchat.messaging.domain.channel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.punchat.messaging.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@DiscriminatorValue("broadcast")
public class BroadcastChannel extends Channel {

    @Column(name = "name")
    private String name;

    @Column(name = "privacy")
    @Enumerated(EnumType.STRING)
    private Privacy privacy;

    @ManyToMany(mappedBy = "channels")
    private List<User> members;

    @JsonProperty
    public List<User> getMembers() {
        return members;
    }

    @JsonIgnore
    public void setMembers(List<User> members) {
        this.members = members;
    }
}
