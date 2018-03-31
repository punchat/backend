package com.github.punchat.uaa.account.jsr303;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Alex Ivchenko
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { PasswordValidator.class })
public @interface Password {
    String message() default "password is required";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
