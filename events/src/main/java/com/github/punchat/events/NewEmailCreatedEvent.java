package com.github.punchat.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewEmailCreatedEvent {
    private String email;
    private String subject;
    private String text;
}
