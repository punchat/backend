package com.github.punchat.uaa.account;

import com.github.punchat.uaa.id.IdService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@Service
public class DaoAccountService implements AccountService {
    private final IdService idService;
    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;

    public DaoAccountService(IdService idService, AccountRepository repository, PasswordEncoder passwordEncoder) {
        this.idService = idService;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
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
            throw new BadCredentialsException("passwords doesn't match");
        }
    }
}
