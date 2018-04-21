package com.github.punchat.messaging.domain.channel;

import com.github.punchat.messaging.domain.AbstractIdentifiableObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Table(name = "channels")
@Entity
@NoArgsConstructor
public class Channel extends AbstractIdentifiableObject {

}
