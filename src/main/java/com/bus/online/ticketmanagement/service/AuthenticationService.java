package com.bus.online.ticketmanagement.service;

import com.bus.online.ticketmanagement.config.security.JwtService;
import com.bus.online.ticketmanagement.model.dto.request.AuthenticationRequest;
import com.bus.online.ticketmanagement.model.dto.response.AuthenticationResponse;
import com.bus.online.ticketmanagement.model.entity.RsaKeyPair;
import com.bus.online.ticketmanagement.model.entity.User;
import com.bus.online.ticketmanagement.repository.RsaKeyPairRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;

import static io.jsonwebtoken.SignatureAlgorithm.RS256;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RsaKeyPairRepository rsaKeyPairRepository;

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

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
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RS256.getFamilyName());
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        String publicKey = Base64.encodeBase64String(keyPair.getPublic().getEncoded());
        String privateKey = Base64.encodeBase64String(keyPair.getPrivate().getEncoded());

        String jwtToken = jwtService.generateAccessToken(user, privateKey);

        RsaKeyPair rsaKeyPair = new RsaKeyPair();
        rsaKeyPair.setPublicKey(publicKey);
        rsaKeyPair.setExpiryTime(LocalDateTime.now().plusMinutes(jwtExpiration));
        rsaKeyPair = rsaKeyPairRepository.save(rsaKeyPair);

        return new AuthenticationResponse(jwtToken, rsaKeyPair.getId());
    }

    /*
     * Remove expired public keys in every 15 min.
     * */
    @Transactional
    @Scheduled(fixedDelay = 300000, initialDelay = 1000)
    public void removeExpiredPublicKeys() {
        rsaKeyPairRepository.deleteByExpiryTimeBefore(LocalDateTime.now());
    }

}
