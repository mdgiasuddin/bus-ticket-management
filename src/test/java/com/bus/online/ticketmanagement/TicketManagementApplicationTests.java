package com.bus.online.ticketmanagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import static io.jsonwebtoken.SignatureAlgorithm.RS256;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TicketManagementApplicationTests {

    @Test
    void testRsaEncryption() throws NoSuchAlgorithmException, InvalidKeySpecException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException {

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RS256.getFamilyName());
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Share public key after encoding with BASE64
        String encodePublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());

        // Save private key in a file or DB after encoding with BASE64
        String encodePrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());

        // Construct public-key from shared string.
        KeyFactory keyFactory = KeyFactory.getInstance(RS256.getFamilyName());
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(encodePublicKey));
        PublicKey sharedPublicKey = keyFactory.generatePublic(publicKeySpec);

        String text = "This is a secret text";

        // Encode the text with the public key and send.
        Cipher encryptCipher = Cipher.getInstance(RS256.getFamilyName());
        encryptCipher.init(Cipher.ENCRYPT_MODE, sharedPublicKey);
        byte[] textBytes = text.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedTextBytes = encryptCipher.doFinal(textBytes);
        String encodedText = Base64.getEncoder().encodeToString(encryptedTextBytes);

        // Construct the private-key from the saved value.
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(encodePrivateKey));
        PrivateKey privateKeyFromSavedValue = keyFactory.generatePrivate(privateKeySpec);

        // Decode the encoded string.
        Cipher decryptCipher = Cipher.getInstance(RS256.getFamilyName());
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKeyFromSavedValue);
        byte[] decryptedTextBytes = decryptCipher.doFinal(Base64.getDecoder().decode(encodedText));
        String decryptedText = new String(decryptedTextBytes, StandardCharsets.UTF_8);

        assertEquals(text, decryptedText);
    }

}
