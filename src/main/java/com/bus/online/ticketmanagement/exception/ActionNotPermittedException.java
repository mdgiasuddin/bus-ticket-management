package com.bus.online.ticketmanagement.exception;

public class ActionNotPermittedException extends ApplicationException {
    public ActionNotPermittedException(String code, String message) {
        super(code, message);
    }
}
