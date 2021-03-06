package com.github.punchat.uaa.account;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface AccountService {
    Account create(String username, String password);

    Account changePassword(String oldPassword, String newPassword);

    Set<Account> getAll();
}
