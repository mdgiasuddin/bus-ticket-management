package com.bus.online.ticketmanagement.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ExceptionResponse handleException(ResourceNotFoundException e) {
        return new ExceptionResponse(e.getCode(), e.getMessage());
    }

    @ResponseStatus(EXPECTATION_FAILED)
    @ExceptionHandler(RuleViolationException.class)
    public ExceptionResponse handleException(RuleViolationException e) {
        return new ExceptionResponse(e.getCode(), e.getMessage());
    }

    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(ActionNotPermittedException.class)
    public ExceptionResponse handleException(ActionNotPermittedException e) {
        return new ExceptionResponse(e.getCode(), e.getMessage());
    }

    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public ExceptionResponse handleException(AuthenticationException e) {
        return new ExceptionResponse("AUTHENTICATION_FAILED", e.getMessage());
    }

    @ResponseStatus(PRECONDITION_FAILED)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionResponse handleException(MethodArgumentNotValidException ex) {

        List<InvalidField> invalidFields = ex.getBindingResult().getFieldErrors()
            .stream()
            .map(
                error -> new InvalidField(error.getField(), error.getDefaultMessage())
            ).toList();

        return new ExceptionResponse(
            "METHOD_ARGUMENT_NOT_VALID",
                invalidFields.toString()
        );

    }
}
