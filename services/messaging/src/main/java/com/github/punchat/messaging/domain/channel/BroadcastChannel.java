package com.github.punchat.messaging.domain.channel;

import com.github.punchat.messaging.domain.role.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@DiscriminatorValue("broadcast")
public class BroadcastChannel extends Channel {

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "privacy")
    @Enumerated(EnumType.STRING)
    private Privacy privacy;

    @OneToOne
    @JoinColumn(name = "default_role_id")
    private Role defaultRole;
}
