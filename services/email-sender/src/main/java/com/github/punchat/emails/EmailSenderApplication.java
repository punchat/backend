package com.github.punchat.emails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class EmailSenderApplication {
    public static void main(final String... args) {
        SpringApplication.run(EmailSenderApplication.class);
    }
}
