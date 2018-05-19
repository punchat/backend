package com.github.punchat.uaa.registration;

import com.github.punchat.events.AccountCreatedEvent;
import com.github.punchat.log.Trace;
import com.github.punchat.uaa.account.Account;
import com.github.punchat.uaa.account.AccountService;
import com.github.punchat.uaa.events.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Trace
@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final AccountManagementService amService;
    private final AccountService accountService;
    private final EventBus eventBus;

    public RegistrationServiceImpl(AccountManagementService amService, AccountService accountService, EventBus eventBus) {
        this.amService = amService;
        this.accountService = accountService;
        this.eventBus = eventBus;
    }

    @Override
    public void register(WorkspaceRegistration registration) {
        if (!amService.emailIsValid(registration.getEmail())) {
            log.debug("email {} is not valid", registration.getEmail());
            throw new EmailIsNotValidException(registration.getEmail());
        }
        if (!amService.codeIsValid(registration.getEmail(), registration.getCode())) {
            log.debug("code {} is not valid", registration.getCode());
            throw new AccessCodeIsNotValidException(registration.getEmail(), registration.getCode());
        }
        Account account = accountService.create(registration.getUsername(), registration.getPassword());
        eventBus.publish(new AccountCreatedEvent(account.getId(), registration.getEmail()));
    }
}
