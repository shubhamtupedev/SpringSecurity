package com.example.springsecurity.Utility;

import java.security.SecureRandom;
import java.util.Base64;

public class SessionIdGenerateUtil {

    // SecureRandom instance for cryptographically strong random number generation
    private static final SecureRandom secureRandom = new SecureRandom();

    // Length of the session ID in bytes (16 bytes = 128 bits)
    private static final int SESSION_ID_LENGTH = 16;

    private static final int RANDOM_NUMBER_LIMIT = 10000000;

    /**
     * Generates a secure, random session ID using SecureRandom.
     *
     * @return A Base64 encoded session ID string.
     */
    public static String generateSessionId() {
        byte[] randomBytes = new byte[SESSION_ID_LENGTH];
        secureRandom.nextBytes(randomBytes); // Fill the byte array with random values

        String base64SessionId = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);

        // Generate a random number
        int randomNumber = secureRandom.nextInt(RANDOM_NUMBER_LIMIT);

        // Append the random number with a dot separator
        return base64SessionId + "." + randomNumber;
    }
}
