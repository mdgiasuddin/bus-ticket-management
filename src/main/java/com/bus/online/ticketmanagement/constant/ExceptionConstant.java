package com.bus.online.ticketmanagement.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionConstant {
    USER_NOT_FOUND("No user found!"),
    STATION_NOT_FOUND("No station found!"),
    STATION_ALREADY_EXISTS("Station already exists with this name!");

    private final String message;
}
