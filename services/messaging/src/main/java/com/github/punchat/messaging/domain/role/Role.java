package com.github.punchat.messaging.domain.role;

import com.github.punchat.messaging.domain.AbstractIdentifiableObject;
import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends AbstractIdentifiableObject {
    @Column(name = "name")
    private String name;

    @ElementCollection(targetClass = Permission.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "permissions")
    @Column(name = "permission")
    private Set<Permission> permissions;

    @ManyToOne(optional = false)
    @JoinColumn(name = "channel", nullable = false)
    private BroadcastChannel channel;

    public Role(String name) {
        this.name = name;
    }
}
