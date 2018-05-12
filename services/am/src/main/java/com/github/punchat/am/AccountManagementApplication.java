package com.github.punchat.am;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class AccountManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountManagementApplication.class, args);
    }
}