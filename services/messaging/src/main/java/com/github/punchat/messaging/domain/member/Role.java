package com.github.punchat.messaging.domain.member;

import com.github.punchat.messaging.domain.AbstractIdentifiableObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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
    private List<Permission> permissions;

    public Role(String name) {
        this.name = name;
    }
}
