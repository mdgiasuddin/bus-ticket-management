package com.bus.online.ticketmanagement.constant;

import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
@Getter
public class ExceptionCode {

    public static final String STATION_NOT_FOUND = "STATION_NOT_FOUND";
    public static final String ROUTE_NOT_FOUND = "ROUTE_NOT_FOUND";//   ("No route found!"),
    public static final String TICKET_COUNTER_NOT_FOUND = "TICKET_COUNTER_NOT_FOUND"; // ("No ticket counter found!"),
    public static final String TRIP_NOT_FOUND = "TRIP_NOT_FOUND"; // ("No trip found!"),
    public static final String ROUTE_MAPPING_NOT_FOUND = "ROUTE_MAPPING_NOT_FOUND";// ("No route mapping found!"),
    public static final String STATION_ALREADY_EXISTS = "STATION_ALREADY_EXISTS";// ("Station already exists with this name!"),
    public static final String SAME_START_AND_END_STATION = "SAME_START_AND_END_STATION";// ("Start and end stations are same!"),
    public static final String MISSING_ENTITIES = "MISSING_ENTITIES"; // ("Some id's are not valid!"),
    public static final String SEAT_ALREADY_BOOKED = "SEAT_ALREADY_BOOKED"; // ("Selected seats are booked already!"),
    public static final String ROUTE_NOT_PERMITTED = "ROUTE_NOT_PERMITTED"; // ("You are not permitted to access this route!"),
    public static final String TRIP_NOT_PERMITTED = "TRIP_NOT_PERMITTED"; // ("You are not permitted to access this trip!"),
    public static final String SEAT_NOT_PERMITTED = "SEAT_NOT_PERMITTED";//("You are not permitted to access this seat!"),
    public static final String INVALID_DATE = "INVALID_DATE"; //("Invalid date for query!"),
    public static final String LOGGED_IN_INFORMATION_MISSING = "LOGGED_IN_INFORMATION_MISSING"; //("No logged in information found!");
}
