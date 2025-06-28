package com.bus.online.ticketmanagement.util;

import lombok.NoArgsConstructor;

import java.security.SecureRandom;
import java.util.Random;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class RandomStringUtil {

    private static final Random random = new SecureRandom();
    private static final String CHAR_SET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String randomString(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(CHAR_SET.charAt(random.nextInt(CHAR_SET.length())));
        }
        return stringBuilder.toString();
    }

}
