package com.movie.store.util;

import com.movie.store.exceptions.NoAppropriateHashingAlgorithmException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashUtil {
    private static final String HASH_ALGORITHM = "SHA-512";

    public static byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static String hashPassword(String password, byte[] salt) {
        StringBuilder hashPassword = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
            messageDigest.update(salt);
            byte[] digest = messageDigest.digest(password.getBytes());
            for (Byte curByte : digest) {
                hashPassword.append(String.format("%02x", curByte));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new NoAppropriateHashingAlgorithmException("Can't hashing password", e);
        }
        return hashPassword.toString();
    }
}
