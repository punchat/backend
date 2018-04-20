package com.github.punchat.messaging.domain.member;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Role {
    @Column(name = "name")
    private String name;
}
