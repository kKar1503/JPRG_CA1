
package jprg_assignment;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Test {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String pw = "Password123";
        String hash1 = generatePassword(pw,salt);
        String hash2 = generatePassword(pw,salt);
        System.out.println(hash1);
        System.out.println(hash2);
        System.out.println(hash1.equals(hash2));
    }
    
    public static String generatePassword(String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest md;
        md = MessageDigest.getInstance("SHA-512");
        md.update(salt);
        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hashedPassword.length; i++) {
            sb.append(Integer.toString((hashedPassword[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
