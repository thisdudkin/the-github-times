package org.earlspilner.users.config;

import lombok.SneakyThrows;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * Use this class to generate secret key for JsonWebToken
 *
 * @author Alexander Dudkin
 */
public class KeyGenerator {

    @SneakyThrows
    static void generateKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[32];
        secureRandom.nextBytes(keyBytes);
        SecretKey secretKey = new SecretKeySpec(keyBytes, "SHA-256");
        System.out.print("256-bit key: ");
        for (byte b : secretKey.getEncoded()) {
            System.out.format("%02x", b);
        }
    }

}
