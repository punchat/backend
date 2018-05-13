package com.github.punchat.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewEmailCreatedEvent {
    private String email;
    private String subject;
    private String text;
}
