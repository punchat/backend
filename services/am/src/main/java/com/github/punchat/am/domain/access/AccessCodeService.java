package com.github.punchat.am.domain.access;

public interface AccessCodeService {
    String generateAccessCode(String email);

    boolean checkAccessCode(String email, String code);
}
