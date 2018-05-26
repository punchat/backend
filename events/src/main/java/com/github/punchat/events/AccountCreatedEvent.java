package com.github.punchat.events;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountCreatedEvent {
    private long userId;
    private String email;
}
