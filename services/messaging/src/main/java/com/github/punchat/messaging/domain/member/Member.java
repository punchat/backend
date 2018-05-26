package com.github.punchat.messaging.domain.member;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.github.punchat.messaging.domain.AbstractIdentifiableObject;
import com.github.punchat.messaging.domain.channel.BroadcastChannel;
import com.github.punchat.messaging.domain.role.Permission;
import com.github.punchat.messaging.domain.role.Role;
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

    @ManyToOne(optional = false)
    @JoinColumn(name = "channel_id", nullable = false)
    @JsonIgnore
    private BroadcastChannel channel;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    @JsonUnwrapped
    private Role role;

    public boolean hasPermission(Permission permission) {
        return getRole().getPermissions().contains(permission);
    }
}
