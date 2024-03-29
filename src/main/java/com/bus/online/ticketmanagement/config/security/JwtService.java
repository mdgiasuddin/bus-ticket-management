
package com.bus.online.ticketmanagement.config.security;

import com.bus.online.ticketmanagement.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static io.jsonwebtoken.SignatureAlgorithm.RS256;

@Service
public class JwtService {

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    public String extractUsername(String token, String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return extractClaim(token, publicKey, Claims::getSubject);
    }

    public boolean isTokenValid(String token, String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return extractExpiration(token, publicKey).after(new Date());
    }

    public Date extractExpiration(String token, String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return extractClaim(token, publicKey, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, String publicKey, Function<Claims, T> claimsResolver) throws NoSuchAlgorithmException, InvalidKeySpecException {
        final Claims claims = extractAllClaims(token, publicKey);
        return claimsResolver.apply(claims);
    }

    public String generateAccessToken(User user, String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return generateAccessToken(new HashMap<>(), user, privateKey);
    }

    public String generateAccessToken(
            Map<String, Object> extraClaims, User user, String privateKey
    ) throws NoSuchAlgorithmException, InvalidKeySpecException {

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (jwtExpiration * 60 * 1000)))
                .signWith(generateEncryptionKey(privateKey), RS256)
                .compact();
    }


    private Claims extractAllClaims(String token, String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {

        return Jwts
                .parserBuilder()
                .setSigningKey(generateDecryptionKey(publicKey))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private PrivateKey generateEncryptionKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance(RS256.getFamilyName());
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        return keyFactory.generatePrivate(keySpec);
    }

    private PublicKey generateDecryptionKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance(RS256.getFamilyName());
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        return keyFactory.generatePublic(keySpec);
    }

}
