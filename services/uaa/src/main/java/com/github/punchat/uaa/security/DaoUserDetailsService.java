package com.github.punchat.uaa.security;

import com.github.punchat.log.Trace;
import com.github.punchat.uaa.account.Account;
import com.github.punchat.uaa.account.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@Trace
public class DaoUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    public DaoUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("username " + username + " not found");
        }
        log.info("loading: {} : {}", account.getPassword(), account.getPassword());
        return  org.springframework.security.core.userdetails.User
                .withUsername(account.getUsername())
                .password(account.getPassword())
                .authorities(Collections.emptyList())
                .build();
    }
}
