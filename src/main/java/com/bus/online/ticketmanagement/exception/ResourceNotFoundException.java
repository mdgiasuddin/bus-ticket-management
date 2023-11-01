package com.bus.online.ticketmanagement.exception;

import com.bus.online.ticketmanagement.constant.ExceptionConstant;

public class ResourceNotFoundException extends ApplicationException {

    public ResourceNotFoundException(ExceptionConstant ex) {
        super(ex.name(), ex.getMessage());
    }

}
