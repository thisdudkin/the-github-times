package org.earlspilner.auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author Alexander Dudkin
 */
public class KeyGenerator {

    public static byte[] generateKey() {
        try {
            SecureRandom secureRandom = new SecureRandom();
            byte[] randomBytes = new byte[32];
            secureRandom.nextBytes(randomBytes);

            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            return digest.digest(randomBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Ошибка при генерации ключа", e);
        }
    }

    public static void main(String[] args) {
        byte[] key = generateKey();
        System.out.println("Сгенерированный ключ: " + bytesToHex(key));
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
