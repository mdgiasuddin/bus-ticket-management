package com.bus.online.ticketmanagement.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionConstant {
    USER_NOT_FOUND("No user found!"),
    STATION_NOT_FOUND("No station found!"),
    ROUTE_NOT_FOUND("No route found!"),
    TICKET_COUNTER_NOT_FOUND("No ticket counter found!"),
    STATION_ALREADY_EXISTS("Station already exists with this name!"),
    SAME_START_AND_END_STATION("Start and end stations are same!"),
    MISSING_ENTITIES("Some id's are not valid!"),
    ROUTE_NOT_PERMITTED("You are not permitted to access this route!"),
    INVALID_DATE("Invalid date for query!"),
    LOGGED_IN_INFORMATION_MISSING("No logged in information found!");

    private final String message;
}
