package com.github.punchat.messaging.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Role {
    @Column(name = "name")
    private String name;

    public Role(String name) {
        this.name = name;
    }
}
