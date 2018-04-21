package com.github.punchat.messaging.domain.member;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.github.punchat.messaging.domain.AbstractIdentifiableObject;
import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "members")
@NoArgsConstructor
public class Member extends AbstractIdentifiableObject {
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonUnwrapped
    private User user;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "channel_id", nullable = false)
    @JsonIgnore
    private BroadcastChannel channel;

    @Embedded
    @AttributeOverrides(
            @AttributeOverride(name = "name", column = @Column(name = "role_name"))
    )
    private Role role;
}
