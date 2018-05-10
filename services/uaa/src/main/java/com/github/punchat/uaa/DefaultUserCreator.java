package com.github.punchat.uaa;

import com.github.punchat.uaa.account.Account;
import com.github.punchat.uaa.account.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserCreator implements CommandLineRunner {
    private final AccountRepository repository;
    private final PasswordEncoder encoder;

    public DefaultUserCreator(AccountRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {
        if (repository.count() == 0) {
            Account defaultUser = new Account();
            defaultUser.setId(0L);
            defaultUser.setUsername("test");
            defaultUser.setPassword(encoder.encode("pass"));
            repository.save(defaultUser);
        }
    }
}
