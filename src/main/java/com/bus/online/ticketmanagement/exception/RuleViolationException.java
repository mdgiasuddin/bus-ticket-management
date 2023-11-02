package com.bus.online.ticketmanagement.exception;

import com.bus.online.ticketmanagement.constant.ExceptionConstant;

public class RuleViolationException extends ApplicationException {

    public RuleViolationException(ExceptionConstant ex) {
        super(ex.name(), ex.getMessage());
    }
}
