package com.bus.online.ticketmanagement.exception;

import java.util.List;

public record ValidationExceptionResponse(
        String code,
        String message,
        List<InvalidField> invalidFields
) {
}

record InvalidField(
        String name,
        String message
) {

}
