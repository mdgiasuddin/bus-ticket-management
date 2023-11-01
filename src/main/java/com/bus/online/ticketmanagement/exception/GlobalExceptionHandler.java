package com.bus.online.ticketmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ExceptionResponse handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ExceptionResponse(e.getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public ExceptionResponse handleAuthenticationException(AuthenticationException e) {
        return new ExceptionResponse("AUTHENTICATION_FAILED", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ExceptionResponse handleSQLConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        return new ExceptionResponse("SQL_CONSTRAINTS_VIOLATED", e.getMessage());
    }

    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationExceptionResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        List<InvalidField> invalidFields = ex.getBindingResult().getFieldErrors()
            .stream()
            .map(
                error -> new InvalidField(error.getField(), error.getDefaultMessage())
            ).toList();

        return new ValidationExceptionResponse(
            "METHOD_ARGUMENT_NOT_VALID",
            "Method argument validation failed.",
            invalidFields
        );

    }
}
