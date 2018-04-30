package com.github.punchat.uaa.account;

import com.github.punchat.events.AccountCreatedEvent;
import com.github.punchat.log.Trace;
import com.github.punchat.uaa.events.EventBus;
import com.github.punchat.uaa.id.IdService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
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
@Trace
@Service
public class DaoAccountService implements AccountService {
    private final IdService idService;
    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final EventBus eventBus;

    public DaoAccountService(IdService idService, AccountRepository repository, PasswordEncoder passwordEncoder, EventBus eventBus) {
        this.idService = idService;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.eventBus = eventBus;
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
        eventBus.publish(new AccountCreatedEvent(account.getId(), LocalDateTime.now(Clock.systemUTC())));
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
