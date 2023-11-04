package com.bus.online.ticketmanagement.exception;

import com.bus.online.ticketmanagement.constant.ExceptionConstant;

public class ActionNotPermittedException extends ApplicationException {
    public ActionNotPermittedException(ExceptionConstant ex) {
        super(ex.name(), ex.getMessage());
    }
}
