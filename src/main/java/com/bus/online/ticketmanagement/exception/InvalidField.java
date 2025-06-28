package com.bus.online.ticketmanagement.exception;

import lombok.NonNull;

public record InvalidField(String name, String message) {

    @NonNull
    public String toString() {
        return "{" +
                "name: '" + name + '\'' +
                ", message: '" + message + '\'' +
                '}';
    }
}
