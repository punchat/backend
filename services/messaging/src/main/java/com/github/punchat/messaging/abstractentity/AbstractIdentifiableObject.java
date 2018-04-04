package com.github.punchat.messaging.abstractentity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public abstract class AbstractIdentifiableObject {

    @Column(name = "id")
    private Long id;
}
