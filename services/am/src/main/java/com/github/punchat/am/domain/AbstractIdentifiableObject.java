package com.github.punchat.am.domain;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "id")
public abstract class AbstractIdentifiableObject {

    @Id
    @Column(name = "id")
    private Long id;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof AbstractIdentifiableObject)) return false;
        AbstractIdentifiableObject that = (AbstractIdentifiableObject) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }
}