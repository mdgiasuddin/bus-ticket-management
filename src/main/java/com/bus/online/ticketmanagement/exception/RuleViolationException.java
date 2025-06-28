package com.bus.online.ticketmanagement.exception;

public class RuleViolationException extends ApplicationException {

    public RuleViolationException(String code, String message) {
        super(code, message);
    }
}
