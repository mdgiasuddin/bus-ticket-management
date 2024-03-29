package com.bus.online.ticketmanagement.service;

import com.bus.online.ticketmanagement.config.security.JwtService;
import com.bus.online.ticketmanagement.model.dto.request.AuthenticationRequest;
import com.bus.online.ticketmanagement.model.dto.response.AuthenticationResponse;
import com.bus.online.ticketmanagement.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationResponse login(AuthenticationRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(), request.password()
                )
        );

        User user = (User) authentication.getPrincipal();

        return generateAccessToken(user);
    }

    private AuthenticationResponse generateAccessToken(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();

        String publicKey = Base64.encodeBase64String(kp.getPublic().getEncoded());
        String privateKey = Base64.encodeBase64String(kp.getPrivate().getEncoded());

        String jwtToken = jwtService.generateAccessToken(user,privateKey);
        return new AuthenticationResponse(jwtToken, publicKey);
    }
}
