package com.github.punchat.uaa.account.jsr303;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Alex Ivchenko
 */
public class PasswordValidator implements ConstraintValidator<Password, String> {
    @Override
    public void initialize(Password constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !value.isEmpty();
    }
}
