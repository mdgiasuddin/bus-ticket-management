package com.bus.online.ticketmanagement.exception;

public class ResourceNotFoundException extends ApplicationException {

    public ResourceNotFoundException(String code, String message) {
        super(code, message);
    }

}
