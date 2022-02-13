
package jprg_assignment;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Authentication {
    private static ArrayList<Credential> credentialsList = null;
    
    public static Object[] authenticate(String username, String password) {
        // Return object[] for authentication
        // Return types: boolean, string, boolean, boolean
        // Boolean : User Exist in Database
        // String  : User's Name
        // Boolean : Admin User
        // Boolean : Users' Access
        Object[] returnObject = {false, "", false, false};
        // Read and Store credentials temporarily in Credential ArrayList
        credentialsList = readCredentials();
        if (credentialsList == null) {
            credentialsList = new ArrayList<>();
        }
        // Check username & password
        for (int i = 0; i < credentialsList.size() || returnObject.equals(false); i++) {
            if (credentialsList.get(i).getUsername().equalsIgnoreCase(username) 
            && verifyPassword(password, 
                credentialsList.get(i).getSalt(), 
                credentialsList.get(i).getPassword())
            ) {
                returnObject[0] = true;
                returnObject[1] = credentialsList.get(i).getName();
                returnObject[2] = credentialsList.get(i).isAdmin();
                returnObject[3] = credentialsList.get(i).isAccess();
            }
        }
        credentialsList.clear();
        return returnObject;
    }
    
    private static ArrayList<Credential> readCredentials(){
        return IOSystem.credentialDeserialization();
    }
    
    // Salt generator to return random salt for each password
    public static byte[] saltGenerator() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        UserActivityLogger.infoLog("New Salt generated.");
        return salt;
    }
    
    // Hashing password with a salt input
    public static String hashPassword(String password, byte[] salt) {
        String generatedPassword = null;
        try {
            MessageDigest md;
            md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < hashedPassword.length; i++) {
                sb.append(Integer.toString((hashedPassword[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
            UserActivityLogger.infoLog("Hashed Password.");
        } catch (NoSuchAlgorithmException e) {
            UserActivityLogger.errLog(
                    "Unable to hash password due to NoSuchAlgorithmException", e
            );
        }
        return generatedPassword;
    }
    
    // Verify Password method to verify passwordInput with hashedPassword in storage
    private static boolean verifyPassword(String passwordInput, byte[] salt, String hashedPassword) {
        boolean verification = false;
        String hashedInput = hashPassword(passwordInput, salt);
        System.out.println(hashedInput);
        System.out.println(hashedPassword);
        if (hashedPassword.equals(hashedInput)) {
            verification = true;
            UserActivityLogger.infoLog("Password verfied true.");
        }
        return verification;
    }
}
