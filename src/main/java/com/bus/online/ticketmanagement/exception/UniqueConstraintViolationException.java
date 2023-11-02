package com.bus.online.ticketmanagement.exception;

import com.bus.online.ticketmanagement.constant.ExceptionConstant;

public class UniqueConstraintViolationException extends ApplicationException{

    public UniqueConstraintViolationException(ExceptionConstant ex) {
        super(ex.name(), ex.getMessage());
    }
}
