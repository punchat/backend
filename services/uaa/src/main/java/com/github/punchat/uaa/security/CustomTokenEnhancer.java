package com.github.punchat.uaa.security;

import com.github.punchat.uaa.account.Account;
import com.github.punchat.uaa.account.AccountRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alex Ivchenko
 */
@Slf4j
public class CustomTokenEnhancer implements TokenEnhancer {
    private final AccountRepository accountRepository;

    public CustomTokenEnhancer(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        if (authentication.isClientOnly()) {
            log.info("user info could not be set");
            return accessToken;
        }
        log.info("adding user info into token");
        Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("user", createUserPayload(authentication));
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }

    private UserPayload createUserPayload(Authentication authentication) {
        return createUserPayload(getUser(authentication));
    }

    private Account getUser(Authentication authentication) {
        String username = authentication.getName();
        Account account = accountRepository.findByUsername(username);
        log.info("found user {}", account);
        return account;
    }

    private UserPayload createUserPayload(Account account) {
        UserPayload payload = new UserPayload();
        payload.setId(account.getId().toString());
        return payload;
    }

    @Getter
    @Setter
    public static class UserPayload {
        private String id;
    }
}
