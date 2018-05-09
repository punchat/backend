package com.github.punchat.am.domain.access;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.punchat.am.domain.AbstractIdentifiableObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonSetter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ACCESS_CODE")
@Entity
public class AccessCode extends AbstractIdentifiableObject {
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "creation_time", nullable = false)
    private LocalDateTime creationTime;
}
