package com.saqaya.usermanagement.business.Services;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Service;

@Service
// The IdGenerationService is a service class responsible for generating unique IDs for users.
// It uses the SHA-1 hashing algorithm to generate a unique ID based on the user's email and a secret key.
public class IdGenerationService {

    // This method generates a unique ID for a user based on their email.
    public String generateId(String email) throws NoSuchAlgorithmException {
        
        // Get an instance of the SHA-1 MessageDigest object
        MessageDigest md = MessageDigest.getInstance("SHA-1");

        // Update the MessageDigest object with the bytes of the user's email
        md.update(email.getBytes(StandardCharsets.UTF_8));

        // Update the MessageDigest object with the bytes of the secret key
        md.update("450d0b0db2bcf4adde5032eca1a7c416e560cf44".getBytes(StandardCharsets.UTF_8));

        // Perform the hash computation and get the resulting bytes
        byte[] digest = md.digest();

        // Convert the byte array into a hexadecimal string
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }

        // Return the hexadecimal string as the unique ID
        return sb.toString();
    }
}
