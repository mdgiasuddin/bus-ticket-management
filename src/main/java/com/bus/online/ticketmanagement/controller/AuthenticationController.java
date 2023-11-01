package com.bus.online.ticketmanagement.controller;

import com.bus.online.ticketmanagement.model.dto.request.AuthenticationRequest;
import com.bus.online.ticketmanagement.model.dto.response.AuthenticationResponse;
import com.bus.online.ticketmanagement.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bus.online.ticketmanagement.constant.APIEndpointConstant.AUTHENTICATION_URL;

@RestController
@RequestMapping(AUTHENTICATION_URL)
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody @Valid AuthenticationRequest request) {
        return authenticationService.login(request);
    }
}
