package com.github.punchat.emails.domain.emails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailMessage {
    private String email;
    private String subject;
    private String text;
}
