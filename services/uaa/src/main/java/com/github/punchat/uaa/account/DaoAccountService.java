package com.github.punchat.uaa.account;

import com.github.punchat.uaa.account.events.AccountCreatedEvent;
import com.github.punchat.uaa.id.IdService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@Service
@EnableBinding(Source.class)
public class DaoAccountService implements AccountService {
    private final IdService idService;
    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final Source source;

    public DaoAccountService(IdService idService, AccountRepository repository, PasswordEncoder passwordEncoder, Source source) {
        this.idService = idService;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.source = source;
    }

    @Override
    @Transactional
    public Account create(@NonNull String username, @NonNull String password) {
        log.info("creating user: {}", username);
        if (repository.existsByUsername(username)) {
            throw new UsernameAlreadyUsed(username);
        }
        String encodedPassword = passwordEncoder.encode(password);
        Account account = new Account(idService.next(), username, encodedPassword);
        source.output().send(MessageBuilder.withPayload(new AccountCreatedEvent(account.getId(), LocalDateTime.now(Clock.systemUTC()))).build());
        return repository.save(account);
    }

    @Override
    public Account changePassword(@NonNull String oldPassword, @NonNull String newPassword) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = repository.findByUsername(username);
        if (passwordEncoder.matches(oldPassword, account.getPassword())) {
            account.setPassword(passwordEncoder.encode(newPassword));
            return repository.save(account);
        } else {
            throw new PasswordsDoNotMatchException();
        }
    }
}
