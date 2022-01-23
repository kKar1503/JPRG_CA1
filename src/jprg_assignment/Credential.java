
package jprg_assignment;

import java.io.Serializable;

public class Credential implements Serializable{
    private String username;
    private String password;
    private String name;
    private boolean admin;
    private boolean access;
    private byte[] salt;
    
    public Credential(String username, String password, String name, boolean admin, boolean access, byte[] salt) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.admin = admin;
        this.access = access;
        this.salt = salt;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        // For each new password, a new salt is generator to add on randomness
        byte[] newSalt = Authentication.saltGenerator();
        // Hash the new password with the new salt
        String newHashedPassword = Authentication.hashPassword(password, newSalt);
        if (newHashedPassword != null) {
            this.password = newHashedPassword;
            // Store the new salt corresponding to this new password
            this.salt = newSalt;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public byte[] getSalt() {
        return salt;
    }

}
