package com.github.punchat.uaa.account;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Alex Ivchenko
 */
public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByUsername(String username);

    boolean existsByUsername(String username);
}
