package com.bus.online.ticketmanagement.controller;

import com.bus.online.ticketmanagement.model.dto.request.AuthenticationRequest;
import com.bus.online.ticketmanagement.model.dto.response.AuthenticationResponse;
import com.bus.online.ticketmanagement.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static com.bus.online.ticketmanagement.constant.APIEndpointConstant.AUTHENTICATION_ENDPOINT;

@RestController
@RequestMapping(AUTHENTICATION_ENDPOINT)
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/v1/login")
    @Operation(summary = "Login and get access-token using username & password.")
    public AuthenticationResponse login(
            @RequestBody @Valid AuthenticationRequest request
    ) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return authenticationService.login(request);
    }
}
