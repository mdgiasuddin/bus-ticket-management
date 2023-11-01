package com.bus.online.ticketmanagement.model.dto.request;

import com.bus.online.ticketmanagement.model.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserCreateRequest(
        @NotBlank
        String name,
        @NotBlank
        String username,
        @NotBlank
        String password,
        @NotBlank
        String mobileNumber,
        @NotBlank
        String nid,
        @NotNull
        Role role
) {
}
