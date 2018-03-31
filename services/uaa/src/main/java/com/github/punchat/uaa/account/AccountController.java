package com.github.punchat.uaa.account;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Alex Ivchenko
 */
@RestController
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public Account create(@RequestBody @Valid Account user) {
        return accountService.create(user.getUsername(), user.getPassword());
    }

    @PutMapping("/accounts/@me/password")
    public Account changePassword(@RequestBody @Valid ChangePasswordPayload payload) {
        return accountService.changePassword(payload.getOldPassword(), payload.getNewPassword());
    }
}
