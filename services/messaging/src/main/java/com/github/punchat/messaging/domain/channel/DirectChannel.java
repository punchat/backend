package com.github.punchat.messaging.domain.channel;

import com.github.punchat.messaging.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
@NoArgsConstructor
@DiscriminatorValue("direct")
public class DirectChannel extends Channel {
    @OneToOne(optional = false)
    private User user;
}
