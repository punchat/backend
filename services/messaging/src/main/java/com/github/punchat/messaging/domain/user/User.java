package com.github.punchat.messaging.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.punchat.messaging.domain.AbstractIdentifiableObject;
import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.channel.DirectChannel;
import com.github.punchat.messaging.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends AbstractIdentifiableObject {
    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = false)
    private DirectChannel channel;
}
