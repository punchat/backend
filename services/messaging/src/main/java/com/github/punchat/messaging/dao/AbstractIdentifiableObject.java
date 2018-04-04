package com.github.punchat.messaging.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
abstract class AbstractIdentifiableObject {

    @Column(name = "id")
    private Long id;
}
