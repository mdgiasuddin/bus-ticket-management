package com.bus.online.ticketmanagement.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CounterMasterRequest(
        @NotBlank
        String name,

        @NotBlank
        @Pattern(regexp = "^[a-z0-9.]{8,}$", message = "Must have 8 characters with lowercase letters or digits or dot(.)")
        String username,

        @NotBlank
        String address,

        @NotBlank
        String mobileNumber,

        @NotBlank
        String nid
) {
}
