package com.github.punchat.am.domain.access;

import com.github.punchat.am.domain.AbstractIdentifiableObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "access_code")
@Entity
public class AccessCode extends AbstractIdentifiableObject {
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "creation_time", nullable = false)
    private LocalDateTime creationTime;
}
