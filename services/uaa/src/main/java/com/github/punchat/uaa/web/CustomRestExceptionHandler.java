package com.github.punchat.uaa.web;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Locale;

/**
 * @author Alex Ivchenko
 */
@ControllerAdvice
public class CustomRestExceptionHandler {
    private final MessageSource messages;

    public CustomRestExceptionHandler(MessageSource messages) {
        this.messages = messages;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiError processValidationError(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        ApiError error = processFieldErrors(fieldErrors);
        error.setMessage("validation error");
        return error;
    }

    private ApiError processFieldErrors(List<FieldError> fieldErrors) {
        ApiError errors = new ApiError();

        for (FieldError error : fieldErrors) {
            String field = error.getField();
            String message = resolveErrorMessage(error);
            errors.addFieldError(field, message);
        }

        return errors;
    }

    private String resolveErrorMessage(String key, Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messages.getMessage(key, args, locale);
    }

    private String resolveErrorMessage(FieldError error) {
        Locale locale = LocaleContextHolder.getLocale();
        return messages.getMessage(error, locale);
    }
}
