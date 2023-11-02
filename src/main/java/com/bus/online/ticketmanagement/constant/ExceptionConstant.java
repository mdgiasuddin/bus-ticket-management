package com.bus.online.ticketmanagement.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionConstant {
    USER_NOT_FOUND("No user found!"),
    STATION_NOT_FOUND("No station found!"),
    STATION_ALREADY_EXISTS("Station already exists with this name!"),
    SAME_START_AND_END_STATION("Start and end stations are same!");

    private final String message;
}
