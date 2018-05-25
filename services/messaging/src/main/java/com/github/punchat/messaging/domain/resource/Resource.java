package com.github.punchat.messaging.domain.resource;

import com.github.punchat.messaging.domain.AbstractIdentifiableObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class Resource extends AbstractIdentifiableObject {
    @Column(name = "text")
    private String text;
}
