package com.github.punchat.uaa.account;

import com.github.punchat.starter.web.error.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@RestController
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public Set<Account> accounts() {
        return accountService.getAll();
    }

    @PutMapping("/accounts/@me/password")
    public Account changePassword(@RequestBody @Valid ChangePasswordPayload payload) {
        return accountService.changePassword(payload.getOldPassword(), payload.getNewPassword());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PasswordsDoNotMatchException.class)
    public ApiError handleException(PasswordsDoNotMatchException ex) {
        ApiError error = new ApiError();
        error.setMessage(ex.getMessage());
        return error;
    }
}
