package org.earlspilner.auth.service;

import lombok.SneakyThrows;

import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * Use this class to generate secret key to sign Json Web Token
 *
 * @author Alexander Dudkin
 */
public class KeyGenerator {

    @SneakyThrows
    public static byte[] generateKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[32];
        secureRandom.nextBytes(randomBytes);

        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        return digest.digest(randomBytes);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
