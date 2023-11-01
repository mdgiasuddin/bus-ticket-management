package com.bus.online.ticketmanagement.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionConstant {
    USER_NOT_FOUND("No user found!"),
    UNIVERSITY_NOT_FOUND("No university found!");

    private final String message;
}
